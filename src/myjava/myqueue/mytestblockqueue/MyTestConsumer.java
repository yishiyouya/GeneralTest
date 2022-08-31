package myjava.myqueue.mytestblockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * 
 * Ïû·ÑÕß
 * 
 */
public class MyTestConsumer implements Runnable {

    private long currentNano = 0L;
    private BlockingQueue<Long> testQueue;
    
    public MyTestConsumer(BlockingQueue<Long> testQueue) {
        this.testQueue = testQueue;
    }
    
    @Override
    public void run() {
        long val = 0L;
        while (true) {
            try {
                val = testQueue.take();
                currentNano = val;
                
                System.out.println("consumer: " + val);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
