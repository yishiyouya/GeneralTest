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
            // �����û��������룬����û�����������conf Ŀ¼�µ�credentials.properties �ļ��У�Ҳ������activemq.xml ������
            connectionFactory.setUserName("system");
            connectionFactory.setPassword("manager");
            // ��������
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // ����Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // ����Ŀ�꣬�ʹ�������Ҳ���Դ�������
            Destination destination = session.createQueue("test");
            // ������Ϣ������
            MessageConsumer consumer = session.createConsumer(destination);
            // ������Ϣ����
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
