package mywork.mylog4j2.mymdslog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * API ������־�߳�
 *
 */
public abstract class AbstractLogThread extends Thread implements MDSLogThread {
    /**
     *  ����״̬ 
     */
    private volatile boolean isRunning = true;
    /**
     * ������д�С
     */
    private BlockingQueue<String> pool = new LinkedBlockingQueue<String>(20000);

    public AbstractLogThread() {
    }

    @Override
    public void log(Throwable e) {

    }
    /**
     * ������Ϣ����
     * 
     * @param data
     * @throws Exception
     */
    public void log(String data) {

        //������������,ͬʱ��debugģʽ�²Ż��¼������־
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
     * ��Ϣ�߼�������
     * @param data
     */
    protected abstract void handlerData(String data);

    /**
     * �Ƿ�ر�
     */
    public void shutdown() {
        isRunning = false;
    }
    /**
     * �Ƿ�ر�
     */
    public void startUp() {
        this.start();
    }
    /**
     * �������־��
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
