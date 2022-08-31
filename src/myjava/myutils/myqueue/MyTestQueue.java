package myjava.myutils.myqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class MyTestQueue {

    private static MyTestQueue instance = null;
    
    public static MyTestQueue getInstance(){
        if (null == instance) {
            instance = new MyTestQueue();
        }
        return instance;
    }
    
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        addQueueVal();
    }

    public static void addQueueVal() throws InterruptedException{
        QueueAdd queueAdd = new QueueAdd();
        queueAdd.run();
        QueueTake queueTake = new QueueTake();
        queueTake.run();
    }
    
    public static void testWait() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            QueueAdd queueAdd = new QueueAdd();
            queueAdd.run();
        }
        for (int i = 0; i < 2; i++) {
            QueueAppend queueAppend = new QueueAppend();
            queueAppend.run();
        }
        for (int i = 0; i < 2; i++) {
            QueueTake queueTake = new QueueTake();
            queueTake.run();
        }
        
        
    }
    
    public static String takeValue() throws InterruptedException {
        return queue.take();
    }
    
    public static void addValue(String val) throws InterruptedException {
        queue.offer(val);
    }
    
    public LinkedBlockingQueue<String> getQueue(){
        return queue;
    }
    
}
