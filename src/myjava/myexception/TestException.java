package myjava.myexception;

public class TestException {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        tryFinally();
    }

    public static void tryFinally(){
        try {
            System.out.println("hhfd");
            int i = 1/0;
            
            System.out.println(i);
        } catch(Exception e){ 
            System.out.println(e.getMessage());
        } finally {
            System.out.println("hh");
        }
    }
    
    public static void isTryZero(){
        try {
            isZero();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
    public static void isZero() throws Exception{
        try {
            int i = 1/0;
        } finally{
            System.out.println("ok");
        }
    }

}
