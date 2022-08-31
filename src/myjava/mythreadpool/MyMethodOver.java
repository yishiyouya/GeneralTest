package myjava.mythreadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ˵��������ִ���У����÷�����������ִ��ʱ�䳬ʱ�Ĳ���
 */
public class MyMethodOver {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                // TODO Auto-generated method stub
                MyMethodOver m = new MyMethodOver();
                return m.getValue();
            }
        });
        executor.execute(future);
        try {
            String result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("����ִ���ж�");
            // future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("Excution�쳣");
            // TODO: handle exception
            future.cancel(true);
        } catch (TimeoutException e) {
            // TODO: handle exception
            System.out.println("����ִ��ʱ�䳬ʱ");
            //future.cancel(true);
        }
        System.out.println("sas");
    }
    public String getValue() {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

            e.printStackTrace();// TODO: handle exception
        }
        return "ssssssssssssssss";
    }
}
