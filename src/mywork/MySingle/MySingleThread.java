package mywork.MySingle;

public class MySingleThread extends Thread{
    
    public static void callTBoolean(){
        SingleInstance instance = SingleInstance.getInstance();
        boolean tOk = false;
        tOk = instance.isOk();
        System.out.println(tOk);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MySingleInstance.callTBoolean();
        
        MySingleThread.callTBoolean();
    }
    
}
