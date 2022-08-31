package myjava.mysocket.proconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class ProductThread implements Runnable{
    private String idInfo;
    private ArrayBlockingQueue<String> queue;
    public ProductThread(String idInfo,ArrayBlockingQueue<String> queue) {
        this.idInfo = idInfo;
        this.queue = queue;
    }
    public void run() {
       /*if(queue.size() > 20){
            queue.wait();
        }*/
        try {
            //接收消息
            Thread.currentThread().sleep(1000);
            System.out.println("开始接收：");
            queue.add(idInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
