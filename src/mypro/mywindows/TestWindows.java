package mypro.mywindows;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestWindows {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testFJsonDay();
    }

    public static void testFJsonDay(){
        //String comd = "{\"1\":\"Runtime.getRuntime().exec(\"calc.exe\")\"}";
        String comd = "Runtime.getRuntime().exec(\"calc.exe\")}";
        Obj obj = new Obj(comd);
        
        String jsonString = JSONObject.toJSONString(obj);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);
        
    }
    
    public static void callCalc(){
        try {
            Process p = Runtime.getRuntime().exec("calc.exe");
            p.waitFor();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Process finished");
    }
    
}

class Obj{
    
    public Obj(String name) {
        super();
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}