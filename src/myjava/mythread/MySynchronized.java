package myjava.mythread;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.openjdk.jol.info.ClassLayout;

public class MySynchronized {
    
    public static void main(String[] args) {
        testClassSync();
    }
    
    public static void testClassSync(){
        try {
            A a = new A();
            System.out.println(ClassLayout.parseInstance(a).toPrintable(a));
            System.out.println(Integer.toHexString(a.hashCode()));
            System.out.println(ClassLayout.parseInstance(a).toPrintable(a));
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable(a));
            }
            A t1 = new A();
            t1.setName("T1");
            t1.start();
            
            Thread.sleep(100);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void changeSyncFlag(){
        boolean flag = false;
        String str = "a";
        synchronized (str) {
            flag = true;
        }
        System.out.println(flag);
    }
    
    //测试成功标识返回
    public static void writeData(IoSession session, String response){
        synchronized (session) {
            session.write(response);
        }
    }
    

    //测试成功标识返回
    public static boolean writeDataFlag(IoSession session, String response){
        boolean flag = false;
        synchronized (session) {
            WriteFuture writeFuture = session.write(response);
            flag = writeFuture.isWritten();
        }
        return flag;
    }
    
    
    
}
