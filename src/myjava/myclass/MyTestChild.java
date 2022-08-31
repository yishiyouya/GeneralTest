package myjava.myclass;

public class MyTestChild extends MyTestParent {
    
    private MyTestChild() {
        isPermitFlag = true;
    }
    
    public boolean getThisIsPermitFlag(){
        return this.isPermitFlag;
    }
    
    public boolean getSuperIsPermitFlag(){
        return super.isPermitFlag;
    }
    
    public static MyTestChild getInstance(){
        return MyTestChildHandler.instance;
    }
    
    private static class MyTestChildHandler {
        static MyTestChild instance = new MyTestChild();
    }
    
    public static void main(String[] args){
        getInstance();
        System.out.println(getInstance().getThisIsPermitFlag());
        System.out.println(getInstance().getSuperIsPermitFlag());
    }
    
    
    
}
