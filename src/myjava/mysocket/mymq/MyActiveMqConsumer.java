package myjava.mysocket.mymq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyActiveMqConsumer {

    public static void main(String[] args) {
        consumerMessage();
    }

    public static void consumerMessage() {
        try {
            String url = "tcp://zhengmeng:61616";
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            // 设置用户名和密码，这个用户名和密码在conf 目录下的credentials.properties 文件中，也可以在activemq.xml 中配置
            connectionFactory.setUserName("system");
            connectionFactory.setPassword("manager");
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // 创建Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建目标，就创建主题也可以创建队列
            Destination destination = session.createQueue("test");
            // 创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);
            // 建立消息监听
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message m) {
                    if (m instanceof TextMessage) {
                        try {
                            System.out.println(((TextMessage) m).getText());
                            System.out.println("suc consume:");
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
