package mywork.myfix.myfiled;

import java.io.File;

import quickfix.DoubleField;

public class MyTestFiled {
    public static void main(String[] args) {
//        DoubleField filed = new DoubleField(44);
//        filed.setValue(12.329999999);
//        System.out.println(filed.getValue());
        testPath();
    }
    
    public static void testPath(){
        File file = new File("");
        String path = file.getAbsolutePath();
        path = path + File.separator + "configuration/comm.properties";
        System.out.println(path);
    }
    
}
