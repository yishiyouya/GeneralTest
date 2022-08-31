package myjava.myqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.emory.mathcs.backport.java.util.concurrent.SynchronousQueue;

public class MyTestQueue {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testPutGetTime();
    }

    public static void testPutGetTime(){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            queue.add("a");
            queue.poll();
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println(cost);
    }
    
    /**
     * ≤‚ ‘ ConcurrentLinkedQueue
     */
    public static void testConcurrentLinkedQueue(){
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("b");
        System.out.println(queue);
    }
    
    /**
     * ≤‚ ‘ DrainTo LinkedBlockingQueue
     */
    public static void testDrainTo(){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("b");
        List<String> list = new ArrayList<String>();
        int dSize = queue.drainTo(list, 10);
        System.out.println(dSize);
        for (String string : list) {
            System.out.println(string);
        }
    }
    
    public static void testSynchronousQueue() throws InterruptedException {
        SynchronousQueue syncQueue = new SynchronousQueue();
        syncQueue.put("a");
        
    }
    
    
}
