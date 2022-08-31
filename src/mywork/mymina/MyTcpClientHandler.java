package mywork.mymina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyTcpClientHandler extends IoHandlerAdapter {
    //�Ự����ʱ����
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session  Created");
    }
    //�Ự��ʱ��������һ������ʱ�ȴ���sessionCreated�������󴥷���������
    public void sessionOpened(IoSession session) throws Exception {
        session.write("Hello....");
    }
    //�����յ���Ϣʱ����
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("�յ���" + message.toString());
        session.write("Received ��" + message.toString());
    }
}
