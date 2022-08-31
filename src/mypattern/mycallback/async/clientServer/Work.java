package mypattern.mycallback.async.clientServer;

public class Work {

    public static void main(String[] args) {
        Client c1 = new Client();
        c1.send(null);
        System.out.println("do others...");
    }
}
