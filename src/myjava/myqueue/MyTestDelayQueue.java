package myjava.myqueue;

import java.util.concurrent.DelayQueue;

/**
 * ÑÓÊ±¶ÓÁÐ£¬µ×²ã£ºpark
 *
 */
public class MyTestDelayQueue {
    
    private static DelayQueue<DelayValue> delayQueue = new DelayQueue<DelayValue>();
    
    public static void main(String[] args) {
        testDelay();
    }
    
    public static void testDelay() {
        try {
            DelayValue delayValue = new DelayValue();
            long currentNano = System.nanoTime();
            delayValue.setTime(currentNano + 1000000);
            delayQueue.add(delayValue);
            
            delayQueue.take();
            long takeNano = System.nanoTime();
            System.out.format("currentNano:%d,\n   takeNano:%d,\n    cost:%d", currentNano, takeNano, (takeNano - currentNano));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
