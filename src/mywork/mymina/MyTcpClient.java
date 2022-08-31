package mywork.mymina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MyTcpClient {

    public static void main(String[] args) {
        //创建IoService实例
        NioSocketConnector connector = new NioSocketConnector();
        //设置过滤链
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast(
            "codec",
            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        //设置处理器
        connector.setHandler(new MyTcpClientHandler());
        //连接地址
        ConnectFuture connect = connector.connect(new InetSocketAddress("localhost", 9123));
    }

}
