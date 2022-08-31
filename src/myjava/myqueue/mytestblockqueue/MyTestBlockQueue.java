package myjava.myqueue.mytestblockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列，延时生产
 *
 */
public class MyTestBlockQueue {
    
    private static BlockingQueue<Long> testQueue = new ArrayBlockingQueue<Long>(100);
    
    public static void main(String[] args) {
        testProCons();
    }
    
    public static void testProCons() {
        Thread producer = new Thread(new MyTestProducer(testQueue), "producer");
        Thread consumer = new Thread(new MyTestConsumer(testQueue), "consumer");
        
        producer.start();
        consumer.start();
        
    }
    
    
}
