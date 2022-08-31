package mypro.myfastjson;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIClient {
    public static void main(String[] args){
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        try {
            Context ctx = new InitialContext(env);
            Object local_obj = ctx.lookup("rmi://localhost/Exploit");
            System.out.println(local_obj);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
