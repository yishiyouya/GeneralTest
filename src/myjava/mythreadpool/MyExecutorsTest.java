package myjava.mythreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorsTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        executorTest();
    }

    public static void executorTest() {
        int threadNum = 3;
        ExecutorService execuSer = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            MyThread thread = new MyThread();
            execuSer.execute(thread);
            System.out.println("Thread StockHandler "+i+" started!");
        }
    }
    
}
