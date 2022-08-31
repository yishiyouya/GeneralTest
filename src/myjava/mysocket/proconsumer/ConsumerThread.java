package myjava.mysocket.proconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerThread implements Runnable{
    
    private ArrayBlockingQueue<String> queue;
    
    public ConsumerThread(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("׼������");
        String idInfo;
        try {
            idInfo = (String) queue.take();
            System.out.println("������"+idInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
