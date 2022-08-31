package mywork.myfastjson;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import myjava.mypojo.MyStudent;
import myjava.mytest.test.pojo.Student;

public class TestFastjson {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        paraJsonStrToList();
    }

    
    public static void paraJsonStrToList() {
        String jsonStr = paraListToJsonStr();
        JSONArray json = JSON.parseArray(jsonStr);
        System.out.println(json);
        for (Object object : json) {
            String res = object.toString();
            System.out.println("res:"+res);
            JSONObject jObject = JSONObject.parseObject(res);
            String idCard = jObject.getString("idCard");
            //会不到的string, 返回 null.
            String resTestNull = jObject.getString("hhh");
            System.out.println(resTestNull+"*********");
            String testNull = null;
            System.out.println(testNull);
            System.out.println(resTestNull == null);
            System.out.println("null".equals(resTestNull));
            System.out.println("idCard: "+idCard);
            /*JSONObject jObject = JSONObject.parseObject((String)object);
            MyStudent st = JSON.toJavaObject(jObject, MyStudent.class);
            System.out.println(st);*/
        }
        /*List<MyStudent> stList = (List<MyStudent>) JSON.toJavaObject(json, MyStudent.class);
        for (int i = 0; i < stList.size(); i++) {
            System.out.println(stList.get(i));
        }*/
    }
    
    public static String paraListToJsonStr() {
        MyStudent stu = new MyStudent("01", "a", 12);
        MyStudent stu1 = new MyStudent("02", "b", 20);
        List stList = new ArrayList();
        stList.add(stu);
        stList.add(stu1);
        
        String res = JSON.toJSONString(stList);
        System.out.println(res);
        
        return res;
    }
    
    public static void testUnderLine(){
        MyStudent stu = new MyStudent("01", "a", 12);
        String stuJson = "{\"id_card\":\"02\", \"name\":\"b\", \"age\":12}";
        MyStudent jStu = JSON.parseObject(stuJson, MyStudent.class);
        System.out.println(jStu.toString());
        System.out.println(jStu.getIdCard());
    }
    
}
