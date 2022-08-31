package myjava.myifelse;

public class MyTestIfelse {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        whileCnt();
    }

    public static void whileCnt() {
        int i = 0;
        while (i++ < 3) {
            System.out.println(i);
        }
    }
    public static void testTwoIf(){
        String a = "a";
        String b = "b";
        if (a.equals("a") && b.equals("b")) {
            System.out.println(a+b);
        } else if (a.equals("a")) {
            System.out.println(a);
        } else if (b.equals("b")) {
            System.out.println(b);
        }
        
        
    }
}
