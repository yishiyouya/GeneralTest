package mywork.myIpSocket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MyTestIp {
    
    public static void main(String[] args) throws UnknownHostException{
        getDefaultLocalHost();
    }
    
    /*
     * ��ȡ����Ip
     */
    public static void getLocalHost() throws UnknownHostException{
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        //InetAddress inetAddress = InetAddress;
        //acceptor.bind(new InetSocketAddress(InetAddress.getLocalHost(), manger.getPort()));
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        inetSocketAddress.getAddress();
        String localHost = InetAddress.getLocalHost().getHostAddress();
        System.out.println(localHost);
    }
    
    /*
     * ��ȡĬ�ϱ���Ip
     */
    public static void getDefaultLocalHost() throws UnknownHostException{
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        String localHost = inetSocketAddress.getAddress().getHostAddress();
        System.out.println(localHost);
    }
}
