package mywork.mylog4j2.mymdslog;
/**
 * MDS行情接口
 *
 */
public interface MDSLogThread {
    /**
     * 是否记录全部日志
     * @param data
     * @param isFullLog
     */
    public void log(String data, boolean isFullLog);
    /**
     * 记录info信息
     * @param data
     */
    public void log(String data);

    /**
     * 记录info信息
     * @param data
     */
    public void log(Throwable e);
    /**
     * 记录info信息
     * @param data
     */
    public void error(String data, Throwable e);
    /**
     * 
     * @param data
     */
    public void warn(String data);
    /**
     * 
     * @param data
     */
    public void error(String data);

    public void info(String string);
    /**
     * 
     * @param data
     */
    public void debug(String data);
    /**
     * 启动线程
     */
    public void startUp();

    public boolean isDebugEnabled();
}
