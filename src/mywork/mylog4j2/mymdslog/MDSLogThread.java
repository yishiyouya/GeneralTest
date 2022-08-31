package mywork.mylog4j2.mymdslog;
/**
 * MDS����ӿ�
 *
 */
public interface MDSLogThread {
    /**
     * �Ƿ��¼ȫ����־
     * @param data
     * @param isFullLog
     */
    public void log(String data, boolean isFullLog);
    /**
     * ��¼info��Ϣ
     * @param data
     */
    public void log(String data);

    /**
     * ��¼info��Ϣ
     * @param data
     */
    public void log(Throwable e);
    /**
     * ��¼info��Ϣ
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
     * �����߳�
     */
    public void startUp();

    public boolean isDebugEnabled();
}
