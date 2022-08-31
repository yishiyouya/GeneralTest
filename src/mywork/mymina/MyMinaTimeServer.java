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
        //创建IoService的实例
        IoAcceptor acceptor = new NioSocketAcceptor();
        //设置过滤链  日志、编解码
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast(
            "codec",
            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        //设置处理器 就是业务逻辑处理的地方
        acceptor.setHandler(new MyTimeServerHandler());
        //让其监听某个端口
        acceptor.bind(new InetSocketAddress(9123));
    }
}
