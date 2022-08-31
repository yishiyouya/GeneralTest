package mypattern.myproducerConsumer;

import java.util.Calendar;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConsumer implements Runnable {
    
    private Logger log = LoggerFactory.getLogger(MyConsumer.class);
    
    private Queue<String> queue = null;

    public MyConsumer(Queue<String> queue) {
        // TODO Auto-generated constructor stub
        this.queue = queue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                if (!queue.isEmpty()) {
                    
                    //log.info("{} 消费了 {}, 队列长度 {} ", Thread.currentThread().getName(), queue.poll(), queue.size());
                    log.info("{} 消费了 {}", Thread.currentThread().getName(), queue.poll());
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }*/
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}