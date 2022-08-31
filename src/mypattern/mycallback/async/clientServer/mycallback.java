package mypattern.mycallback.async.clientServer;

public interface mycallback {
    void onData(Object message);
    void onError(Exception e);
}
