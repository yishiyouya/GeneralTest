package mywork.mylog4j2.mymdslog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *API 日志处理线程
 */
public class AsyncLogThread extends AbstractLogThread {

    /**
     * 日志
     */
    private final static Logger API_COMM_Log = LoggerFactory.getLogger("API_INFO_Log");
    private final static Logger API_ALL_Log = LoggerFactory.getLogger("API_ALL_Log");
    private static MDSLogThread instance = new AsyncLogThread();

    private AsyncLogThread() {
        setName("AsyncLogThread");
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
        AsyncLogThread.instance = instance;
    }

    @Override
    protected void handlerData(String data) {
        API_COMM_Log.info(data);
    }

    @Override
    public void warn(String data) {
        if (API_ALL_Log.isWarnEnabled()) {
            API_ALL_Log.warn(data);
        }
    }

    @Override
    public void error(String data) {
        if (API_ALL_Log.isErrorEnabled()) {
            API_ALL_Log.error(data);
        }
    }

    @Override
    public void debug(String data) {
        if (API_ALL_Log.isDebugEnabled()) {
            API_ALL_Log.debug(data);
        }
    }

    @Override
    public void info(String data) {
        if (API_ALL_Log.isInfoEnabled()) {
            API_ALL_Log.info(data);
        }
    }

    @Override
    public void error(String data, Throwable e) {
        API_ALL_Log.error(data, e);
    }

    @Override
    public boolean isDebugEnabled() {
        return API_ALL_Log.isDebugEnabled();
    }

    @Override
    public void log(String data, boolean isFullLog) {
        info(data);
    }
}
