package mywork.MySingle;

import java.io.Serializable;

public class MySingleInstance {
    public static void main(String[] args) {
        callTBoolean();
    }

    public static void callTBoolean(){
        SingleInstance instance = SingleInstance.getInstance();
        instance.setOk(true);
        
        SingleInstance instance2 = SingleInstance.getInstance();
        
        boolean tOk = false;
        tOk = instance2.isOk();
        System.out.println(tOk);
    }
    
}
