package myjava.mythreadpool;

public class MyThread implements Runnable {

    @Override
    public void run() {
        int i = 0;
        while(true){
            System.out.println("=="+i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
