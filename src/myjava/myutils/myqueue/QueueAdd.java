package myjava.myutils.myqueue;

import java.util.concurrent.LinkedBlockingQueue;


public class QueueAdd implements Runnable{

    @Override
    public void run() {
        System.out.println(this.getClass().getName()+" started!");
        LinkedBlockingQueue<String> queue = MyTestQueue.getInstance().getQueue();
        while(true){
            for (int k = 0; k < 10; k++) {
                queue.offer("a"+k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
