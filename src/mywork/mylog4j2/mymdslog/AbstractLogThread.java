package mywork.mylog4j2.mymdslog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * API 性能日志线程
 *
 */
public abstract class AbstractLogThread extends Thread implements MDSLogThread {
    /**
     *  运行状态 
     */
    private volatile boolean isRunning = true;
    /**
     * 缓存队列大小
     */
    private BlockingQueue<String> pool = new LinkedBlockingQueue<String>(20000);

    public AbstractLogThread() {
    }

    @Override
    public void log(Throwable e) {

    }
    /**
     * 放入消息队列
     * 
     * @param data
     * @throws Exception
     */
    public void log(String data) {

        //队列容量控制,同时在debug模式下才会记录丢弃日志
        if (!pool.offer(data)) {
            MDSAPILOG.warn("The log queue is full and the message is discarded:" + data);
        }
    }

    public void run() {
        List<String> list = new ArrayList<String>();

        while (isRunning) {
            try {
                if (pool.isEmpty()) {
                    String data = pool.take();
                    list.add(data);
                }
                pool.drainTo(list);
                for (String data : list)
                    handlerData(data);
                list.clear();
            } catch (Throwable e) {
                MDSAPILOG.error("Log failure", e);
            }
        }
    }

    /**
     * 消息逻辑处理方法
     * @param data
     */
    protected abstract void handlerData(String data);

    /**
     * 是否关闭
     */
    public void shutdown() {
        isRunning = false;
    }
    /**
     * 是否关闭
     */
    public void startUp() {
        this.start();
    }
    /**
     * 缓存的日志数
     * @return
     */
    public int size() {
        return pool.size();
    }

    @Override
    public void warn(String data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(String data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(String data) {

    }

    @Override
    public void info(String string) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(String data, Throwable e) {
        // TODO Auto-generated method stub

    }
}
