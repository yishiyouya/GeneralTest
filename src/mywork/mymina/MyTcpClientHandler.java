package mywork.mymina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyTcpClientHandler extends IoHandlerAdapter {
    //会话创建时触发
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session  Created");
    }
    //会话打开时触发（第一次连接时先触发sessionCreated函数，后触发本函数）
    public void sessionOpened(IoSession session) throws Exception {
        session.write("Hello....");
    }
    //当接收到消息时触发
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("收到：" + message.toString());
        session.write("Received ：" + message.toString());
    }
}
