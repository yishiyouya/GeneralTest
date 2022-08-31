package myjava.myatomic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {
    
    public static void main(String[] args){
        testAtomiInteger();
    }
    
    public static void testAtomiInteger(){
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            int icr = count.getAndIncrement();
            System.out.println(icr);
        }
        for (int i = 0; i < 10; i++) {
            int dec = count.getAndDecrement();
            System.out.println(dec);
        }
    }
}
