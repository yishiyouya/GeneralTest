package mywork.mymina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MyMinaTimeServer {
    public static void main(String[] args) throws IOException {
        //����IoService��ʵ��
        IoAcceptor acceptor = new NioSocketAcceptor();
        //���ù�����  ��־�������
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast(
            "codec",
            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        //���ô����� ����ҵ���߼�����ĵط�
        acceptor.setHandler(new MyTimeServerHandler());
        //�������ĳ���˿�
        acceptor.bind(new InetSocketAddress(9123));
    }
}
