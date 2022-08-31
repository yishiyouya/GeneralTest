package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import myjava.mytest.util.Constants;
import myjava.mytest.util.LogUtil;



public class MyServerSocket {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        ThreadPoolExecutor pool =
            new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        try {
            Socket socket = null;
            serverSocket = new ServerSocket(12001);
            System.out.println("���������������");
            for (int i = 0; i < 10; i++) {
                socket = serverSocket.accept();
                System.out.println("---" + (i + 1) + "�ſͻ������ӳɹ�");
                if (i == 9) {
                    i = 0;//������������ڱ�֤��ʹ�ͻ��������Ͽ������ӣ�������Ҳ����һֱ���ڵȴ�״̬��������ѭ�������������ѭ��socket�ͽ��ر�
                }
                pool.execute(new ReaderHandle(socket));
            }
            new Thread(new WriteHandle(socket)).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

class ReaderHandle implements Runnable {
    public Socket socket;
    public ReaderHandle(Socket socket) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("�ȴ��û�����:");
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            String read = "";
            try {
                while ((read = buffer.readLine()) != "quit") {//����û�û������quit����ǿ���жϿ���̨����ô�Ϳ��Ա��ֳ�������
                    System.out.println(Thread.currentThread().getName() + "����" + read);
                    //�����̴߳���
                    if(read.contains(Constants.OPRATE_REGIT_ID)){
                        read = read.substring(read.indexOf(Constants.OPRATE_REGIT_ID) + 3, read.length()).trim();
                        try {
                            //MyWorkPool.saveIdInfo(read);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            LogUtil.log("insert idInfo: "+read, Constants.LOG_FILE_FAIL_TYPE);
                            e.printStackTrace();
                        }
                        break;
                    }else if(read.contains(Constants.OPRATE_QUERY_NAME)){
                        read = read.substring(read.indexOf(Constants.OPRATE_QUERY_NAME) + 6, read.length()).trim();
                        try {
                            //MyWorkPool.getPersonInfo(read);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            } catch (SocketException e) {
                // TODO: handle exception
                System.out.println("�ͻ��������Ͽ�����");//���û�ǿ���жϿ���̨ʱ���׳�SokcetException
            }
            socket.close();
            in.close();
            buffer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class WriteHandle implements Runnable {//��������������Socket����д�����ݣ����õĻ�ֻ��Ҫ���ӵ����ͻ��˼���
    public Socket socket;
    public WriteHandle(Socket socket) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(out);
            System.out.println("������ͻ������죺");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            while (true) {//��֤����˿���һֱ���ͻ�������
                String str = buffer.readLine();
                System.out.println("     �㣺" + str);
                printWriter.println("���Է���������Ϣ:" + str);
                printWriter.flush();
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
