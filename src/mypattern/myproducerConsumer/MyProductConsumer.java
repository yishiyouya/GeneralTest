package mypattern.myproducerConsumer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * 阻塞队列timeOut获取期间：
 *  如果队列中有数据，则立刻取出，不会等待到timeOut设置的值
 *  否则，等待timeOut设置的值后，返回 null
 */

public class MyProductConsumer {

    private Queue<String> queue = null;
    private boolean flag = true;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new MyProductConsumer().test();
    }

    public void test() {
        MyProduct product = new MyProduct(queue);
        MyConsumer consumer = new MyConsumer(queue);
        
        for (int i = 0; i < 1; i++) {
            
            Thread p = new Thread(product);
            p.start();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            Thread c = new Thread(consumer);
            c.setName("cosumer-"+i);
            c.start();
        }
    }

    public MyProductConsumer() {
        //queue = new LinkedBlockingQueue<String>();
        queue = new ConcurrentLinkedQueue<String>();
    }
}
