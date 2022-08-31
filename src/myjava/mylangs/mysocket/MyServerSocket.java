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
            System.out.println("服务端已启动……");
            for (int i = 0; i < 10; i++) {
                socket = serverSocket.accept();
                System.out.println("---" + (i + 1) + "号客户端连接成功");
                if (i == 9) {
                    i = 0;//这里的作用在于保证即使客户端主动断开了连接，服务器也可以一直处于等待状态（不跳出循环），否则出了循环socket就将关闭
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
            System.out.println("等待用户接入:");
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            String read = "";
            try {
                while ((read = buffer.readLine()) != "quit") {//如果用户没有输入quit或者强行中断控制台，那么就可以保持持续连接
                    System.out.println(Thread.currentThread().getName() + "处理" + read);
                    //工作线程处理
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
                System.out.println("客户端主动断开连接");//当用户强行中断控制台时会抛出SokcetException
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

class WriteHandle implements Runnable {//这个类的作用是向Socket里面写入数据，想用的话只需要连接单个客户端即可
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
            System.out.println("正在与客户端聊天：");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            while (true) {//保证服务端可以一直跟客户端聊天
                String str = buffer.readLine();
                System.out.println("     你：" + str);
                printWriter.println("来自服务器的消息:" + str);
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
