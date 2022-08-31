package myjava.myutils.myqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueAppend implements Runnable{
    @Override
    public void run() {
        System.out.println(this.getClass().getName()+" started!");
        LinkedBlockingQueue<String> queue = MyTestQueue.getInstance().getQueue();
        while (true) {
            for (int j = 0; j < 100; j++) {
                try {
                    queue.offer("append");
                    System.out.println("append: "+queue.size());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
