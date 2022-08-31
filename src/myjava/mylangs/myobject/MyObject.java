package myjava.mylangs.myobject;


public class MyObject {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        claEql();
    }

    public static void claEql() {
        MyStudent st1 = new MyStudent("a");
        MyStudent st2 = st1;
        boolean eql = false;
        eql = st1 == st2;
        System.out.println(eql);
    }
    
    public static void objEql() {
        Object obj1 = "a";
        Object obj2 = new String("a");
        boolean eql = false;
        eql = obj1 == obj2;
        System.out.println(eql);
    }
    
    
    
}
