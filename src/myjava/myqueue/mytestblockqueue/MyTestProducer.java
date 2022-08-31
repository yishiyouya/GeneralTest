package myjava.myqueue.mytestblockqueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 
 * ЩњВњеп
 * 
 */
public class MyTestProducer implements Runnable {

    private long interval = 10000L;//ns
    private long currentNano = System.nanoTime();
    
    private BlockingQueue<Long> testQueue;
    
    public MyTestProducer(BlockingQueue<Long> testQueue) {
        this.testQueue = testQueue;
    }

    @Override
    public void run() {
        long proVal = 0L;
        long currentCost = 0L;
        while (true) {
            try {
                proVal = System.nanoTime();
                currentCost = proVal - currentNano;
                if (currentCost > interval) {
                    currentNano = proVal;
                    testQueue.offer(proVal);
                }
                //Thread.sleep(1000);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
