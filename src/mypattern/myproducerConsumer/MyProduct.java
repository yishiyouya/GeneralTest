package mypattern.myproducerConsumer;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyProduct implements Runnable {
    
    private Logger log = LoggerFactory.getLogger(MyProduct.class);
    
    private Queue<String> queue = null;

    public MyProduct(Queue<String> queue) {
        // TODO Auto-generated constructor stub
        this.queue = queue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int i = 0;
        while (true) {
            i++;
            /*if (i++ > 10000) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                i = 0;
            }*/
            for (int j = 0; j < 100; j++) {
                j = j++;
            }
            String n = String.valueOf(i);
            try {
                queue.offer(n);
                //System.out.println("生产了     " + n);
                //log.info("生产了     " + n);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}