package mypattern.mycallback.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
  * Future
  * Futures��һ������ĸ������ʾһ��ֵ����ֵ������ĳһ���ÿ��á�
  * һ��FutureҪô��ü�����Ľ����Ҫô��ü���ʧ�ܺ���쳣��
  * Java��java.util.concurrent���и�����Future�ӿڣ���ʹ��Executor�첽ִ�С�
  * ��
  * ������Ĵ��룬ÿ����һ��Runnable����ExecutorService.submit()�����ͻ�õ�һ���ص���Future��
  * ����ʹ��������Ƿ�ִ�У����ַ���������ͬ���ȴ��ߴ�������ɡ�
 */
public class NettyTest {

    public static void main(String[] args) {

        //ʵ��һ��Callable�ӿ�  
        Callable<Netty> c = new Callable<NettyTest.Netty>() {

            @Override
            public Netty call() throws Exception {

                //���������ҵ���߼�����  

                //�õ�ǰ�߳�����1�뿴��Ч��
                Thread.sleep(1000);
                return new Netty("����");
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);

        //�ǵ�Ҫ��submit��ִ��Callable����  
        Future<Netty> fn = es.submit(c);
        //һ��Ҫ���������������ȻexecutorService.isTerminated()��Զ��Ϊtrue
        es.shutdown();

        //����ѭ���ȴ����������  ����Ѿ�������� isDone����true  
        while (!fn.isDone()) {
            try {
                //������Ϻ󷵻صĽ��  
                Netty nt = fn.get();
                System.out.println(nt.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class Netty {

        private String name;

        private Netty(String name) {
            this.name = name;
        }

    }
}