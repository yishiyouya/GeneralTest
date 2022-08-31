package mypattern.mycallback.async.clientServer;

public class Server implements Runnable {
    mycallback c;
    Object message;
    public Server(mycallback cl, Object o) {
        c = cl;
        message = o;
    }
    @Override
    public void run() {
        try {
            c.onData(message + " after server");
        } catch (Exception e) {
            c.onError(e);
        }
    }
}
