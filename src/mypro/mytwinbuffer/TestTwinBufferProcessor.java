package mypro.mytwinbuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ˫���洦����
 * 
 * Ӧ�ó�����
 * һ����д������Ķ�������̣߳�����һ�Ӷ����л�ȡ���������д�����������ȫ���������̵߳Ĳ���������
 * ͬʱ����������С����롱�͡�ȡ������Ҫ�����̰߳�ȫ�����⣬������Ϊ����ͬ�����ƣ������������������ͬ�����ƣ����ɱ����Ҫ���Ǽ��������⣻
 * ���ڸ߲��������£��Ե����еĴ�ȡ�Ĳ�������Ӿ��ڴ��������⣬�Ӷ�Ӱ�칤���̵߳ĵ���Ч�ʡ�
 * 
 * ��ˣ����ʵ��һ��˫���д�������
 * 1��һ�����У����ڴ���ⲿ�����ύ��������󣬴˶�������󲢷��Ĵ��������
 * 2���������У����ڴ�Ŵ�һ��������ת�ƹ����Ĵ�������󣬴˶�������һ�����������̵߳�ȡ��������
 * 3�����ȴ�����������һ�������еĴ��������ÿ�ΰ�ָ��������ȡ����������Ϊһ��Collection<Runnable>��Ȼ�����������С�
 * 4��������������ThreadPoolExecutor���й��죬�߱������Ѳ���ִ�е��ص㡣
 * 
 * ������
 * 1��������󲢷���ȡ������������ڴ������⡣
 * 2�����Խ��ⲿ�ύ�ĵ������󣬻��ܳ������ύ�����̴߳������ڹ����߳�ͨ���ϲ����㡢����ִ�С���������Ԥ������ֶΣ���ߴ���Ч�ʡ�
 * 
 * 
 */
public abstract class TestTwinBufferProcessor<T> extends Thread {

    //һ������
    private RequestBuffer<T> buffer = null;

    //һ�����д�����
    private TaskProcessor processor = null;

    //�����߳�ÿ�δ�һ��������ȡ���Ĵ������������
    private int GetSize = 100;

    public TestTwinBufferProcessor() {
        this(1000, 100, 1000, 1);
    }

    /**
     * ���캯��
     * @param bufferSize һ����������
     * @param getSize �����߳�ÿ�δ�һ������ȡ���ļ�¼��
     * @param poolSize ������������
     * @param threadSize �������湤���̸߳���
     */
    public TestTwinBufferProcessor(int bufferSize, int getSize, int poolSize, int threadSize) {
        buffer = new RequestBuffer<T>(bufferSize);
        this.GetSize = getSize;
        processor = new TaskProcessor(poolSize, threadSize);
    }

    /**
     * ��������
     */
    public void run() {

        while (true) {
            try {
                //ÿ�θ���getSize�Ĵ�С��ȡһ��������Ϣ 
                Collection<T> requestsList = new ArrayList<T>();

                /*
                 * ��������������Ϊ�� ���� �п��е��̣߳��Ҵ�ʱһ�������д��ڴ����������
                 * �򣬰�ָ���������������󣬴�һ������ȡ�� ������������档
                 * 
                 * ���򣬼���һ�����������д���������Ҳ�ݲ����뵽�������档
                 * ��Ϊ�����������������й����̶߳��ڹ����У�������ǰ�������һ��������뵽�������棬Ҳ���ᱻ����
                 * ֻ���ڶ��������γ��µĶѻ������ԣ������������һ�����н��л��ۣ��Ӷ���ߵ����߳�ÿ�ε��ȵ�����Ч����
                 */
                if ((processor.isTaskEmpty() || processor.isExistIdleThreads())
                    && buffer.size() > 0) {

                    for (int i = 0; i < GetSize; i++) {
                        //ͨ���������ķ�ʽ��ȡ�����е���Ϣ
                        T data = buffer.poll();

                        if (data != null) {
                            requestsList.add(data);
                        } else {
                            break;
                        }
                    }
                }

                //������λ�ȡ��������Ϣ�����ύ���������
                if (requestsList.size() > 0) {
                    processor.process(handle(this, requestsList));
                } else {
                    //�������û�д�һ�������л�ȡ������������������߳̽���ȴ�״̬��
                    //�ȴ������ѻ򵽴�timeoutʱ��
                    synchronized (this) {
                        wait(10);
                    }
                }

            } catch (Throwable e) {
            }
        } //while (true) 
    }

    /**
     * ���ѵ����߳�
     * 
     * 1���ⲿ���򽫴������������һ�������ỽ��һ�ε����̣߳�
     * 2���������湤���߳����һ������Ĵ����ỽ��һ�ε����̣߳�
     */
    public synchronized void wakeup() {
        notify();
    }

    /**
     * �ⲿ���򽫴������������һ������
     * @param data
     * @return
     */
    public boolean put(T data) {
        boolean isSuccess = buffer.offer(data);
        wakeup();
        return isSuccess;
    }

    /**
     * �������̻߳�ȡ�Ĵ��������� ��װ��Task�����ύ��������
     * @param reqList �������¼�
     * @return ת��ΪTask�����񼯺�
     */
    protected abstract Collection<TestTask> handle(TestTwinBufferProcessor twin,
        Collection<T> requestsList);

}

/**
 * һ�����У����ڴ���ⲿ�����ύ���������
 * @param <T>
 */
class RequestBuffer<T> {

    private BlockingQueue<T> pool = null;

    //ִ�б�����г��޵ȴ����ʱ�䣨s��
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
 * �������У����ڴ�Ŵ�һ��������ת�ƹ����Ĵ��������
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

        //�����������
        TestProcessPool workQueue = new TestProcessPool(capacity);

        //�����̳߳ط���
        return new ThreadPoolExecutor(threadSize, threadSize, 0L, TimeUnit.MILLISECONDS, workQueue);
    }

    /**
     * �����¼�����
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
     * �Ƿ���ڿ����̡߳�
     * @return ��������е��߳�����getActiveCount�� С�� �Ѵ������߳�����getCorePoolSize�������ʾ��ǰ���ڿ����̡߳�
     */
    public boolean isExistIdleThreads() {
        return serv.getActiveCount() < serv.getCorePoolSize();
    }

    /**
     *  �Ƿ��Ѿ�û�пɴ��������
     *  
     *  ������пռ� ���� ������ʼ�ռ䣬���ʾ��û�пɴ��������
     */
    public boolean isTaskEmpty() {
        return serv.getQueue().remainingCapacity() == this.capacity;
    }

    /**
     * ��������е������� С�� �����߳��������ʾ�����������а����������
     * @return
     */
    public boolean needMoreTask() {
        return (this.capacity - serv.getQueue().remainingCapacity()) < serv.getCorePoolSize();
    }

}
