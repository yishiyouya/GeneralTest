package mypattern.mycallback.async.clientServer;

public class Client implements mycallback {
    int count = 0;
    @Override
    public void onData(Object message) {
        count++;
        System.out.println("received message:" + message.toString());
    }

    @Override
    public void onError(Exception e) {
        System.out.println("error!");
    }
    public void send(String message) {
        Thread thread = new Thread(new Server(Client.this, message));
        thread.start();
        Thread thread1 = new Thread(new Server(Client.this, message));
        thread1.start();
    }
}
