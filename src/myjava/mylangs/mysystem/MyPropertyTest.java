package myjava.mylangs.mysystem;

import java.util.Properties;

public class MyPropertyTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        getLocalIp();
    }

    
    public static void getLocalIp() {
        Properties pro = System.getProperties();
        System.out.println(pro.toString());
    }
}
