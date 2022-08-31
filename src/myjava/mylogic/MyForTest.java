package myjava.mylogic;

import myjava.mytest.test.pojo.Student;

public class MyForTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testInstanceOf();
    }

    public static void testInstanceOf() {
        Object stu = new Student();
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if (stu instanceof Student) {
                
            }
        }
        long cost = System.nanoTime() - start;
        System.out.println(cost);
        

        Student stu1 = new Student("a", 10);
        
        int testS = 10;
        long start1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if (stu1.getAge() == testS) {
                
            }
        }
        long cost1 = System.nanoTime() - start1;
        System.out.println(cost1);
    }
    
    public static void forTestMultiEqualElseIf(){
        int i = 10;
        int j = 10;
        if(i > 3){
            System.out.println("i > 3");
        } else if(j > 2) {
            System.out.println("j > 3");
        } else {
            System.out.println("i 10 ");
        }
        System.out.println("end");
    }
    
    public static void elseForVar(){
        int acctType = 1;
        if (acctType != 0 && acctType != 1) {
            System.out.println("not good");
        }
    }
    
    public static void forTestElseIf(){
        int i = 10;
        int j = 10;
        if(i > 3){
            System.out.println("i > 3");
        } else if(j > 2) {
            System.out.println("j > 3");
        } else {
            System.out.println("i 10 ");
        }
        System.out.println("end");
    }
    
    public static void forTestContinue(){
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.println(i);
        }
    }
    
}
