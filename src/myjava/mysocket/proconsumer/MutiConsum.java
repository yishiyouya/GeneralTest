package myjava.mysocket.proconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MutiConsum {

    static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);
    //为多生产者和多消费者分别开创的线程池
    static ThreadPoolExecutor productPool = new ThreadPoolExecutor(
                                        10, 20, 60,
                                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5),
                                        new ThreadPoolExecutor.CallerRunsPolicy());
    static ThreadPoolExecutor consumerPool = new ThreadPoolExecutor(
                                        10, 20, 60,
                                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5),
                                        new ThreadPoolExecutor.CallerRunsPolicy());

    ExecutorService exService = Executors.newFixedThreadPool(3);

    public static void producer(String idInfo) {
        productPool.execute(new ProductThread(idInfo, queue));
    }

    public static void consumer() {
        consumerPool.execute(new ConsumerThread(queue));
    }

}
