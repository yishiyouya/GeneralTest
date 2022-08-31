package mywork.mylog4j2.mymdslog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ����ͳ����־�߳�
 *
 */
public class PerformanceLogThread extends AbstractLogThread {

    /**
     * ��־
     */
    private final static Logger log = LoggerFactory.getLogger("PerformanceLog");

    private static MDSLogThread instance = new PerformanceLogThread();

    private PerformanceLogThread() {
        setName("PerformanceLogThread");
    }

    /**
     * @return the instance
     */
    public static MDSLogThread getInstance() {
        return instance;
    }

    /**
     * ����Ĭ��ʵ��.
     */
    public static void setInstance(MDSLogThread instance) {
        PerformanceLogThread.instance = instance;
    }

    protected void handlerData(String data) {
        log.debug(data);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void log(String data, boolean isFullLog) {
        log.info(data);
    }
}
