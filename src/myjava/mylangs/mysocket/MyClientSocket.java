package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClientSocket {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12001);
            new Thread(new WrteHandle(socket)).start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class WrteHandle implements Runnable {
    public Socket socket;
    public WrteHandle(Socket socket) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter write = new PrintWriter(out);
            System.out.println("客户端输入:");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String str = buffer.readLine();
            System.out.println("    你：" + str);
            write.println("收到客户端的消息:" + str);
            write.flush();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
