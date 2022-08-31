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
            System.out.println("----------------�����ִ�С��_ʼ��������----------------");
            Socket socket = serverSocket.accept();//�_ʼ����
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //��ȡ��������
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("����server�ˡ�client����Ϊ��" + info);

            }

            socket.shutdownInput();
            //server����client�ش���Ϣ
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("client���á����Ѿ����ܵ������Ϣ");
            printWriter.flush();
            //�ر���Դ
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
