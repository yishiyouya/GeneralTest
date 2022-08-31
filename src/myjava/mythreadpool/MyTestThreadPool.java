package myjava.mythreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class MyTestThreadPool {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testSynchronizedPool();
    }

    public static void testThreadPool() {
        ThreadPoolExecutor ThreadPoolExecutor = new ThreadPoolExecutor(0, 0, 0, null, null);
    }
    
    public static void testSynchronizedPool() {
        BlockingQueue<Integer> queue = new SynchronousQueue<Integer>();
        System.out.print(queue.offer(1) + " ");
        System.out.print(queue.offer(2) + " ");
        System.out.print(queue.offer(3) + " ");
        try {
            System.out.print(queue.take() + " ");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(queue.size());
    }
}
