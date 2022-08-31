package mypro.myprotostuff;

import java.util.Collection;
import java.util.Map;

import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class TestProtostuff {

    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public static void ProtostuffSerial() {

    }

}

class TestSerial{
    String name;
    int age;
    public TestSerial(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public TestSerial() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}