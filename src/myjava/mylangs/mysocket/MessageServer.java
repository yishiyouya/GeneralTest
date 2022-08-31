package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("----------------服务端执行。開始监听请求----------------");
            Socket socket = serverSocket.accept();//開始监听
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //获取请求内容
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("我是server端。client请求为：" + info);

            }

            socket.shutdownInput();
            //server端向client回传信息
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("client您好。我已经接受到你的信息");
            printWriter.flush();
            //关闭资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (Exception exception) {

        }

    }
}
