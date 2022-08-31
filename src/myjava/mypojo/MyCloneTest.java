package myjava.mypojo;

public class MyCloneTest {
    
    public static void main(String[] args) throws CloneNotSupportedException {
        testClass();
        testClassClone();
    }
    
    
    public static void testClass() throws CloneNotSupportedException {
        MyStudent myStu = new MyStudent("123", "a", 12);
        MyStudent stuCpy = myStu;
        myStu.setName("b");
        System.out.println(myStu.hashCode() + " " + myStu);
        System.out.println(stuCpy.hashCode() + " " + stuCpy);
    }
    
    public static void testClassClone() throws CloneNotSupportedException {
        MyStudent myStu = new MyStudent("123", "a", 12);
        MyStudent stuCpy = (MyStudent) myStu.clone();
        myStu.setName("b");
        System.out.println(myStu.hashCode() + " " + myStu);
        System.out.println(stuCpy.hashCode() + " " + stuCpy);
    }
    
}
