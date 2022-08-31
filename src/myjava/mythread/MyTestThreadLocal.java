package myjava.mythread;



public class MyTestThreadLocal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testThreadLocal();
    }

    public static void testThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("a");
        String res = threadLocal.get();
        System.out.println(res);
    }
}
