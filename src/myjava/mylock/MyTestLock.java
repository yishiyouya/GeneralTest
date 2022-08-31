package myjava.mylock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class MyTestLock {

    public static void main(String[] args) {
        
    }
 
    public static void testWriteReadLock() {
        ReadWriteLock writeLock = new ReadWriteLock() {
            
            @Override
            public Lock writeLock() {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public Lock readLock() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
    
}
