package myjava.myunsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class MyTestUnsafe {

    public static int data = 0;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        testUnsafeCost();
    }

    public static void testUnsafeCost() throws NoSuchFieldException,
        SecurityException,
        IllegalArgumentException,
        IllegalAccessException, InterruptedException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long dataAddress = unsafe.staticFieldOffset(MyTestUnsafe.class.getDeclaredField("data"));
        long start = System.nanoTime();
        for (int j = 0; j < 1000 * 1000; j++) {
            int anInt = unsafe.getInt(MyTestUnsafe.class, dataAddress);
            unsafe.compareAndSwapInt(
                MyTestUnsafe.class,
                dataAddress,
                anInt,
                anInt + 1);
        }
        double cost = (System.nanoTime() - start) / 1000 / 1000;
        System.out.println(cost);
    }
    
    public static void testUnsafeCostMultiThread() throws NoSuchFieldException,
    SecurityException,
    IllegalArgumentException,
    IllegalAccessException, InterruptedException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        final Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        final long dataAddress = unsafe.staticFieldOffset(MyTestUnsafe.class.getDeclaredField("data"));
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        int anInt = unsafe.getInt(MyTestUnsafe.class, dataAddress);
                        while (!unsafe.compareAndSwapInt(
                            MyTestUnsafe.class,
                            dataAddress,
                            anInt,
                            anInt + 1)) {
                            anInt = unsafe.getInt(MyTestUnsafe.class, dataAddress);
                        }
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(data);
    }

}
