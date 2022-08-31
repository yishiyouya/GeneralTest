package myjava.myutils.myqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueTake implements Runnable{
    @Override
    public void run() {
        System.out.println(this.getClass().getName()+" started!");
        while (true) {
            LinkedBlockingQueue<String> queue = MyTestQueue.getInstance().getQueue();
            for (int j = 0; j < 100; j++) {
                try {
                    //queue.take();
                    String tv = queue.take();
                    System.out.println("take: "+queue.size()+" "+tv+" "+System.currentTimeMillis()%1000);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
