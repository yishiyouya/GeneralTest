package myjava.mysocket.mymq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyActiveMqProducer {

    public static void main(String[] args) {
        produceMessage();
    }

    public static void produceMessage() {
        try {
            //����һ�����ӹ���
            String url = "tcp://zhengmeng:61616";
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            // �����û��������룬����û�����������conf Ŀ¼�µ�credentials.properties �ļ��У�Ҳ������activemq.xml ������
            connectionFactory.setUserName("system");
            connectionFactory.setPassword("manager");
            // ��������
            Connection connection = connectionFactory.createConnection();
            connection.start();
            /*����Session���������ͣ�
                                    ��һ�������Ƿ�ʹ������:����Ϣ����������Ϣ�ṩ�ߣ�����Ϣ����������Ϣʱ����Ϣ�����ߵȴ���Ϣ�����ȷ�ϣ�û��
                                    ��Ӧ���׳��쳣����Ϣ���ͳ��������������
                                    �ڶ���������Ϣ��ȷ��ģʽ��
              AUTO_ACKNOWLEDGE �� ָ����Ϣ�ṩ����ÿ���յ���Ϣʱ�Զ�����ȷ�ϡ���Ϣֻ��Ŀ�귢��һ�Σ�����������п�����Ϊ��
                            �����ʧ��Ϣ��
              CLIENT_ACKNOWLEDGE �� ����Ϣ������ȷ���յ���Ϣ��ͨ��������Ϣ��acknowledge()��������֪ͨ��Ϣ�ṩ���յ�����Ϣ��
              DUPS_OK_ACKNOWLEDGE �� ָ����Ϣ�ṩ������Ϣ������û��ȷ�Ϸ���ʱ���·�����Ϣ������ȷ��ģʽ���ں��������յ���
                            ������Ϣ����*/
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // ����Ŀ�꣬�ʹ�������Ҳ���Դ�������
            Destination destination = session.createQueue("test");
            // ������Ϣ������
            MessageProducer producer = session.createProducer(destination);
            // ���ó־û���DeliveryMode.PERSISTENT ��DeliveryMode.NON_PERSISTENT
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            // ������Ϣ
            String text = "Hello ActiveMQ!";
            TextMessage message = session.createTextMessage(text);
            // ������Ϣ��ActiveMQ
            producer.send(message);
            // �ر���Դ
            session.close();
            connection.close();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
