package myjava.mycurreent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyTestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        testCountDownLatch();
    }
    
    public static void testCountDownLatch(){
        try {
            final CountDownLatch latch = new CountDownLatch(2);

            System.err.println("开始启动线程！");
            
            new Thread() {
                public void run() {
                    System.err.println(Thread.currentThread().getName() + " 开始计算");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName() + " 计算结束");
                    latch.countDown();
                };
            }.start();
            new Thread() {
                public void run() {
                    System.err.println(Thread.currentThread().getName() + " 开始计算");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName() + " 计算结束");
                    latch.countDown();
                };
            }.start();
            
            System.err.println("开始等待两个子线程计算任务");
            boolean timeOutRes = latch.await(10000, TimeUnit.MILLISECONDS);
            latch.await();
            System.err.println("两个子线程计算完成，主线程返回 .是否完成：" + timeOutRes);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
