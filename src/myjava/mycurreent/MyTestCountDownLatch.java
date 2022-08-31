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

            System.err.println("��ʼ�����̣߳�");
            
            new Thread() {
                public void run() {
                    System.err.println(Thread.currentThread().getName() + " ��ʼ����");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName() + " �������");
                    latch.countDown();
                };
            }.start();
            new Thread() {
                public void run() {
                    System.err.println(Thread.currentThread().getName() + " ��ʼ����");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName() + " �������");
                    latch.countDown();
                };
            }.start();
            
            System.err.println("��ʼ�ȴ��������̼߳�������");
            boolean timeOutRes = latch.await(10000, TimeUnit.MILLISECONDS);
            latch.await();
            System.err.println("�������̼߳�����ɣ����̷߳��� .�Ƿ���ɣ�" + timeOutRes);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
