package myjava.mythread;

import org.openjdk.jol.info.ClassLayout;

public class A extends Thread {
    
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(ClassLayout.parseInstance(this).toPrintable(this));
            }
        }
    }
    
}
