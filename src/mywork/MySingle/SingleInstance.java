package mywork.MySingle;

import java.io.Serializable;

public class SingleInstance implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3958979809602539708L;

    private static boolean isOk = false;
    
    private SingleInstance(){
        
    }
    
    public static SingleInstance getInstance() {
        return SingleInstanceHelper.instance;
    }
    
    private static class SingleInstanceHelper {
        private static final SingleInstance instance = new SingleInstance();
    }
    
    
    public static boolean isOk() {
        return isOk;
    }

    public static void setOk(boolean isOk) {
        SingleInstance.isOk = isOk;
    }

    /*
     * ��ֹ���л��ļ��ٶ�ȡ��hashCode�仯
     */
    protected Object readResolve(){
        return SingleInstance.getInstance();
    }
    
}
