package mywork.mylog4j2.mymdslog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 性能统计日志线程
 *
 */
public class PerformanceLogThread extends AbstractLogThread {

    /**
     * 日志
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
     * 设置默认实例.
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
