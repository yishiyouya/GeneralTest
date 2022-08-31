package myjava.myutils.mycollection;

public class TestChild {
    
    public static void main(String[] args){
        Child child = new Child();
        child.setMale(false);
        System.out.println(child.isMale);
        
        Parent childP = new Child();
        childP.setMale(false);
        System.out.println(childP.isMale);
        
    }
    
}
