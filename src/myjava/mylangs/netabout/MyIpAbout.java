package myjava.mylangs.netabout;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class MyIpAbout {
    public static void main(String[] args){
        try {
            System.out.println(getLocalHostLANAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static InetAddress getLocalHostLANAddress() throws Exception {
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
}
