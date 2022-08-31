package myjava.mythreadpool;

public class MyTestProcessor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        cpuNum();
    }

    public static void cpuNum(){
        int num = Runtime.getRuntime().availableProcessors()+1;
        System.out.println(num);
        
    }
}
