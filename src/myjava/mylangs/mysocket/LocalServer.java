package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LocalServer {

    public static void main(String[] args) {
        LocalServer localServer = new LocalServer();
        localServer.startServer();
    }

    public void startServer() {
        ThreadPoolExecutor pool =
            new ThreadPoolExecutor(
                5,
                10,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100));
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("server started¡­¡­");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("client " + socket.getRemoteSocketAddress() + " connected.");
                //pool.execute(new serverHandle(socket));
                new Thread(new serverHandle(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class serverHandle implements Runnable {

    BufferedReader reader = null;
    BufferedWriter writer = null;

    private Socket incoming;

    public serverHandle(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream);
                out.println("Enter Bye to Exit.");
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equalsIgnoreCase("Bye")) {
                        done = true;
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
