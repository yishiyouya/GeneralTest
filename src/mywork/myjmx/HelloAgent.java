package mywork.myjmx;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
 
public class HelloAgent {
    
    public static void main(String[] args) throws Exception {
        //create mbean server
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
         
        //create object name
        ObjectName objectName = new ObjectName("jmxBean:name=hello");
         
        //create mbean and register mbean 
        server.registerMBean(new Hello(), objectName);
         
         
        /**
         * JMXConnectorServer service 
         */
        //��仰�ǳ���Ҫ������ȱ�٣�ע��һ���˿ڣ���url�󣬿ͻ��˾Ϳ���ʹ��rmiͨ��url��ʽ������JMXConnectorServer 
        Registry registry = LocateRegistry.createRegistry(1099);
         
        //����JMXServiceURL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        //����JMXConnectorServer
        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);  
        //����
        cs.start();
        System.out.println("JMX helloMBean started!");
    }
    
}