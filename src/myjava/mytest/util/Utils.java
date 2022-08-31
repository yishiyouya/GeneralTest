package myjava.mytest.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Utils {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(getCurTime());
    }

    public static InetAddress getLocalHostLANAddress(){
        try {
            InetAddress candidateAddress = null;
            // �������е�����ӿ�
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // �����еĽӿ����ٱ���IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// �ų�loopback���͵�ַ
                        if (inetAddr.isSiteLocalAddress()) {
                            // �����site-local��ַ����������
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local���͵ĵ�ַδ�����֣��ȼ�¼��ѡ��ַ
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // ���û�з��� non-loopback��ַ.ֻ�������ѡ�ķ���
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getCurDate(){
        Date date = new Date();
        String curDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        curDate = sdf.format(date); 
        return curDate;
    }
    
    public static String getCurTime(){
        Date date = new Date();
        String curDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curDate = sdf.format(date); 
        return curDate;
    }
}
