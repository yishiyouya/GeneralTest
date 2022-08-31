package myjava.myclass;

import java.net.URL;

public class MyClassName {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MyClassName mcN = new MyClassName();
        mcN.getClassPath();
        
    }

    public void getClassName(){
        System.out.println(this.getClass().getSimpleName());
    }
 
    public void getClassPath(){
        URL path = null;
        path = this.getClass().getResource("");
        System.out.println(path.toString());
    }
}
