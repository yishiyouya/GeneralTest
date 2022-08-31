package mypro.mytwinbuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 双缓存处理器
 * 
 * 应用场景：
 * 一般队列处理器的多个工作线程，是逐一从队列中获取任务对象进行处理，并发度完全依靠工作线程的并发个数；
 * 同时由于向队列中“放入”和“取出”需要考虑线程安全的问题，无论人为考虑同步控制，还是依靠容器本身的同步机制，不可避免的要考虑加锁的问题；
 * 而在高并发场景下，对单队列的存取的操作，会加剧内存锁的问题，从而影响工作线程的调度效率。
 * 
 * 因此，设计实现一个双队列处理器：
 * 1）一级队列，用于存放外部程序提交的请求对象，此队列面向大并发的存入操作。
 * 2）二级队列，用于存放从一级队列中转移过来的待处理对象，此队列面向一定数量工作线程的取出操作。
 * 3）调度处理器，负责将一级队列中的待处理对象，每次按指定的数量取出，并汇总为一个Collection<Runnable>，然后放入二级队列。
 * 4）二级队列利用ThreadPoolExecutor进行构造，具备多消费并发执行的特点。
 * 
 * 设计理念：
 * 1）降低因大并发存取给缓存带来的内存锁问题。
 * 2）可以将外部提交的单笔请求，汇总成批后，提交工作线程处理。便于工作线程通过合并计算、批量执行、利用批量预编译等手段，提高处理效率。
 * 
 * 
 */
public abstract class TestTwinBufferProcessor<T> extends Thread {

    //一级队列
    private RequestBuffer<T> buffer = null;

    //一级队列处理器
    private TaskProcessor processor = null;

    //调度线程每次从一级队列中取出的待处理任务个数
    private int GetSize = 100;

    public TestTwinBufferProcessor() {
        this(1000, 100, 1000, 1);
    }

    /**
     * 构造函数
     * @param bufferSize 一级缓存容量
     * @param getSize 调度线程每次从一级缓存取出的记录数
     * @param poolSize 二级缓存容量
     * @param threadSize 二级缓存工作线程个数
     */
    public TestTwinBufferProcessor(int bufferSize, int getSize, int poolSize, int threadSize) {
        buffer = new RequestBuffer<T>(bufferSize);
        this.GetSize = getSize;
        processor = new TaskProcessor(poolSize, threadSize);
    }

    /**
     * 调度任务
     */
    public void run() {

        while (true) {
            try {
                //每次根据getSize的大小获取一批请求信息 
                Collection<T> requestsList = new ArrayList<T>();

                /*
                 * 如果二级任务队列为空 或者 有空闲的线程，且此时一级缓存中存在待处理的请求；
                 * 则，按指定数量待处理请求，从一级队列取出 并放入二级缓存。
                 * 
                 * 否则，即便一级缓存中仍有待处理任务，也暂不搬入到二级缓存。
                 * 因为，如果二级缓存的所有工作线程都在工作中，即便提前将任务从一级缓存搬入到二级缓存，也不会被处理，
                 * 只是在二级缓存形成新的堆积。所以，不如继续留在一级队列进行积累，从而提高调度线程每次调度的批量效果。
                 */
                if ((processor.isTaskEmpty() || processor.isExistIdleThreads())
                    && buffer.size() > 0) {

                    for (int i = 0; i < GetSize; i++) {
                        //通过非阻塞的方式获取队列中的信息
                        T data = buffer.poll();

                        if (data != null) {
                            requestsList.add(data);
                        } else {
                            break;
                        }
                    }
                }

                //如果本次获取到请求信息，则提交到处理过程
                if (requestsList.size() > 0) {
                    processor.process(handle(this, requestsList));
                } else {
                    //如果本次没有从一级缓存中获取到待处理任务，则调度线程进入等待状态；
                    //等待被唤醒或到达timeout时间
                    synchronized (this) {
                        wait(10);
                    }
                }

            } catch (Throwable e) {
            }
        } //while (true) 
    }

    /**
     * 唤醒调度线程
     * 
     * 1）外部程序将待处理任务放入一级缓存后会唤醒一次调度线程；
     * 2）二级缓存工作线程完成一个任务的处理后会唤醒一次调度线程；
     */
    public synchronized void wakeup() {
        notify();
    }

    /**
     * 外部程序将待处理任务放入一级缓存
     * @param data
     * @return
     */
    public boolean put(T data) {
        boolean isSuccess = buffer.offer(data);
        wakeup();
        return isSuccess;
    }

    /**
     * 将调度线程获取的待处理任务 包装成Task任务，提交二级缓存
     * @param reqList 待处理事件
     * @return 转换为Task的任务集合
     */
    protected abstract Collection<TestTask> handle(TestTwinBufferProcessor twin,
        Collection<T> requestsList);

}

/**
 * 一级队列，用于存放外部程序提交的请求对象
 * @param <T>
 */
class RequestBuffer<T> {

    private BlockingQueue<T> pool = null;

    //执行报告队列超限等待间隔时间（s）
    public final static int DEFUALT_OVEROW_SLEEP_TIME = 3;

    RequestBuffer(int capacity) {
        pool = new LinkedBlockingQueue<T>(capacity);
    }

    public boolean offer(T data) {
        boolean isOffer = false;
        try {
            isOffer = pool.offer(data, DEFUALT_OVEROW_SLEEP_TIME, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
        }
        return isOffer;
    }

    public T poll() {
        return pool.poll();
    }

    public int size() {
        return pool.size();
    }
}

/**
 * 二级队列，用于存放从一级队列中转移过来的待处理对象
 * @author liwei
 *
 */
class TaskProcessor {

    private int capacity = 1000;
    private int threadSize = 1;

    protected ThreadPoolExecutor serv = null;

    TaskProcessor(int capacity, int threadSize) {
        this.capacity = capacity;
        this.threadSize = threadSize;

        serv = createExecutorService();
        serv.prestartAllCoreThreads();
    }

    private ThreadPoolExecutor createExecutorService() {

        //创建任务队列
        TestProcessPool workQueue = new TestProcessPool(capacity);

        //创建线程池服务
        return new ThreadPoolExecutor(threadSize, threadSize, 0L, TimeUnit.MILLISECONDS, workQueue);
    }

    /**
     * 处理事件对象
     */
    public void process(TestTask task) throws RejectedExecutionException {
        serv.execute(task);
    }

    public void process(Collection<TestTask> tasks) throws RejectedExecutionException {
        for (Runnable t : tasks)
            serv.execute(t);

        if (tasks != null)
            tasks.clear();
    }

    /**
     * 是否存在空闲线程。
     * @return 如果工作中的线程数（getActiveCount） 小于 已创建的线程数（getCorePoolSize），则表示当前存在空闲线程。
     */
    public boolean isExistIdleThreads() {
        return serv.getActiveCount() < serv.getCorePoolSize();
    }

    /**
     *  是否已经没有可处理的任务
     *  
     *  如果空闲空间 等于 容器初始空间，则表示已没有可处理的任务
     */
    public boolean isTaskEmpty() {
        return serv.getQueue().remainingCapacity() == this.capacity;
    }

    /**
     * 如果队列中的任务数 小于 工作线程数，则表示可以往队列中搬入更多任务
     * @return
     */
    public boolean needMoreTask() {
        return (this.capacity - serv.getQueue().remainingCapacity()) < serv.getCorePoolSize();
    }

}
