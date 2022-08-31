package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageClient {
    public static void main(String[] args) {
        try {
            //发送到8888端口
            Socket socket=new Socket("127.0.0.1", 8888);
            //输出流
            OutputStream outputStream=socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write("server端你好，我是client。");
            printWriter.flush();
            socket.shutdownOutput();//这个不能少。！

            //接受server端回传的信息
            InputStream inputStream=socket.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String info;
            while ((info=bufferedReader.readLine())!=null) {
                System.out.println("我是client。server端返回：："+info);
                
            }
            //关闭资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
