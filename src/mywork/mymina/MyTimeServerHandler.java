package mywork.mymina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyTimeServerHandler extends IoHandlerAdapter {
    
    //����һ������  
    public static int count = 0;

    //�������쳣ʱ�ᴥ�������ﲻ���κδ���������ӡһ�£�
    public void execptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
    
    //�����յ�����ʱ����
    public void messageReceived(IoSession session, Object message) {
        String mes = message.toString();
        //�����ܵ���������quitʱ�����û��������2��ʱ�ر�����
        if (mes.trim().equalsIgnoreCase("quit") || count > 2) {
            System.out.println("�����˳�ʱ�������");
            session.write("�����˳�ʱ���������������");
            session.close();
            return;
        }
        //��ͻ��˻ظ�һ����ǰʱ��
        Date date = new Date();
        session.write(date.toString());
        System.out.println("message writen��" + mes);
        count++;
    }

}
