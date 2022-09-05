package myjava.mylangs.myinteger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import myjava.mytest.test.pojo.Student;


public class MyTestInteger {

    private static Random random = new Random();

    private static DateTimeFormatter sessionIdRandom = DateTimeFormatter.ofPattern("ddHHmmss");
    
    //两位递增序列数
    protected static AtomicInteger atomicInx = new AtomicInteger();
    
    public static void main(String[] args) throws Exception {
        getMaxValue();
    }

    public static void getMaxValue() {
        Integer max = Integer.MAX_VALUE;
        System.out.println(max);
    }

    public static void testIntegerAdd() {
        int i = 10;
        System.out.println(i++ + i--);
    }
    
    public static void testMoveLeft() {
        int i = 1024;
        int res = i << 1;
        System.out.println(res);
    }
    
    public static void testSessionId() throws Exception {
        Map<Long, String> sessionIdMap = new HashMap<Long, String>();
        /*long cuTime = getSessionIdTime();
        for (int i = 1; i < 10; i++) {
            sessionIdMap.put(cuTime * 100 + i, "");
        }*/
        
        long res = 0l;
        for (int i = 1; i < 150; i++) {
            res = getNewSessionId(sessionIdMap);
            System.out.println(i + ":" + res);
        }
        
    }
    
    private synchronized static long getNewSessionId(Map<Long, String> sessionIdMap) throws Exception {
        long newSessionId = 0L;
        long currentValue = getSessionIdTime() * 100 + getRandomIncrInx();
        Set<Long> sessionIds = sessionIdMap.keySet();
        int repeatCount = 0;
        
        //如果重复，尝试生成新sessionId，三次后如再重复，则抛出异常
        if (sessionIdMap.containsKey(currentValue)) {
            repeatCount++;
            for (int i = 0; i < 3; i++) {
                currentValue = getSessionIdTime() * 100 + getRandomIncrInx();
                System.out.println("currentValue:" + currentValue);
                System.out.println("sessionIds:" + sessionIds);
                if (sessionIdMap.containsKey(currentValue)) {
                    repeatCount++;
                } else {
                    newSessionId = currentValue;
                    break;
                }
            }
            System.out.println(repeatCount+"=======");
            if (repeatCount > 3) {
                throw new Exception("The system is busy. Try again later……");
            }
            System.out.println("newSessionId:" + newSessionId+ " " + currentValue);
        } else {
            newSessionId = currentValue;
        }
        
        sessionIdMap.put(newSessionId, "");
        
        return newSessionId;
    }
    
    public static void testRandomIncrInx() {
        for (int i = 0; i < 120; i++) {
            int res = getRandomIncrInx();
            System.out.println(i + " " + res);
        }
    }
    
    public static synchronized int getRandomIncrInx() {
        int idx = atomicInx.get();
        if (idx < 99) {
            atomicInx.getAndIncrement();
        } else {
            atomicInx.set(1);
        }
        idx = atomicInx.get();
        return idx;
    }
    
    public static void tryMoveRight() {
        int maxI = Integer.MAX_VALUE;
        System.out.println(maxI);
        //int max = 9550100000;
        int j = 1;
        int resJ = (j << 16) - 1;
        System.out.println(resJ);
        System.out.println(10 & resJ);
        
        int i = 65535;
        int res = i >> 16;
        System.out.println(res);
    }
    
    public static void tryFinally(){
        System.out.println("hhfd");
        try {
            System.out.println("hhfd");
            int i = 1/0;
            System.out.println(i);
        } catch(Exception e){ 
            System.out.println(e.getMessage());
        } finally {
            System.out.println("hh");
        }
    }
    
    public static void printRandomInt() {
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(100000000));
        }
    }
    
    public static void getRandomInt() {
        Set<Integer> intSet = new HashSet<Integer>();
        int item = 0;
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 1000; j++) {
                item++;
            }
            //int res = (int) (System.nanoTime() % 1000 + System.nanoTime() / 3 + random.nextInt(10000000));
            int res = (int) (System.nanoTime() % 1000 + random.nextInt(100000000));
            intSet.add(res);
        }
        System.out.println(intSet.size());
    }
    
    public static void chargeIntBoolean() {
        /*Integer i = 0;
        boolean is = (boolean)i;
        if (i) {
            System.out.println();
        }*/
    }
    
    public static void testIntegerEx(){
        int arr[] = {2,10,4,3,4,2,3};
        //int arr[] = {2,2,4,4,3,3,1};
        int result = arr[0];
        for (int j = 1; j < arr.length; j++) {
            result = result ^ arr[j];
            System.out.print(result+",");
        }
    }
    
    public static void testIntegerEqual(){
       Integer a = new Integer(2);
       int b = 2;
       System.out.println(a == b);
    }
    
    public static void testAddD(){
        int index = 0;
        Student s = new Student();
        /*s.setAge(index++);
        System.out.println(s.getAge());*/
        int res = index++;
        int res1 = index++;
        System.out.println(res);
        System.out.println(res1);
        System.out.println(index);
    }
    
    public static void indexPlusAddCom(){
        int index = 0;
        while (index++ < 5) {
            System.out.println(index);
        }
    }
    
    public static void indexPlusAdd(){
        int index = 1;
        for (int i = 0; i < 10; i++) {
            index++;
            System.out.println(index);
        }
    }
    
    /**
     * transOccurTime(20200709121010l);
     * @param occurTime
     * @return
     */
    public static long transOccurTime(long occurTime) {
        long resOcurTime = 0;
        resOcurTime = occurTime % 1000000;
        System.out.println(resOcurTime);
        return resOcurTime;
    }
    
    public static void longTStr(){
        long endTime = 201908082300l;
        String strEndTime = String.valueOf(endTime);
        System.out.println(strEndTime);
        String seconds =  String.valueOf(endTime).substring(12, 14);
        System.out.println(seconds);
    }
    
    public static void addP(){
        int k = 1 % 0;
        int i = 1;
        int j = i++;
        System.out.println(j);
    }
    
    public static void getCal() {
        int i = 2<<15;
        System.out.println(i);
    }
    

    /**
     * 获取格式化毫秒时间作为sessionId随机数
     * yyyyMMddHHmmss
     * @return
     */
    public static long getSessionIdTime() {
        LocalDateTime dateTime = LocalDateTime.now();//代替calendar
        long date = Long.valueOf(sessionIdRandom.format(dateTime));
        return date;
    }
    
}
