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
            //���͵�8888�˿�
            Socket socket=new Socket("127.0.0.1", 8888);
            //�����
            OutputStream outputStream=socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write("server����ã�����client��");
            printWriter.flush();
            socket.shutdownOutput();//��������١���

            //����server�˻ش�����Ϣ
            InputStream inputStream=socket.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String info;
            while ((info=bufferedReader.readLine())!=null) {
                System.out.println("����client��server�˷��أ���"+info);
                
            }
            //�ر���Դ
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
