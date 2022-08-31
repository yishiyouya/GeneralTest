package mywork.myRateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import myjava.myio.MyTestFile;

import com.google.common.util.concurrent.RateLimiter;
import scala.actors.threadpool.Arrays;

public class MyTestRateLimiter {

    public static void main(String[] args) {
        testLogRateLimiter();
    }

    public static void testLogRateLimiter() {
        try {
            RateLimiter rateLimiter = RateLimiter.create(200);
            int count = 50*2000;
            ArrayList<Double> list = new ArrayList<Double>(count); 
            System.out.println(System.currentTimeMillis());
            for (int i = 0; i < count; i++) {
                //list.add(rateLimiter.acquire(1));
                System.out.println(toMiscro(rateLimiter.acquire(1)));
                //list.add(toMiscro(rateLimiter.acquire(1)));
                //System.out.format("rate:%s\t%3.8f\n", rateLimiter.getRate(), rateLimiter.acquire(1));
                //System.out.format("rate:%s\t%3.8f\n", rateLimiter.getRate(), toMiscro(rateLimiter.acquire(1)));
                //System.out.println(rateLimiter.acquire(1));
            }
            MyTestFile.testWriteFile(list, "result.log");
            System.out.println(System.currentTimeMillis());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void testRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(2000, 3, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            System.out.format("idx:%d\t%3.8f\n", i, rateLimiter.acquire(1));
        }
        System.out.println(System.currentTimeMillis());
    }
    
    /**
     * Ô¤ÈÈ
     */
    public static void testWarmRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(2000, 3, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 40; i++) {

            System.out.format("%.8f\n", toMiscro(rateLimiter.acquire(1)));
        }
        System.out.println(System.currentTimeMillis());
    }
    
    public static double toMiscro(double sec) {
        return sec*1000*1000;
    }
    
    /**
     * tryAcquire
     */
    public static void testTryAcquireRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        while (true) {
            System.out.println("get 1 tokens: " + rateLimiter.tryAcquire(1, 1, TimeUnit.MICROSECONDS));
            System.out.println("get 1 tokens: " + rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS));
            System.out.println("get 1 tokens: " + rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS));
            System.out.println("end");
        }
    }
    
}
