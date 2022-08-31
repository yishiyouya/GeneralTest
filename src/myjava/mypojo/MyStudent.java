package myjava.mypojo;

import java.io.Serializable;

public class MyStudent implements Cloneable {
    
    /**
     * –Ú¡–ªØ 
     */
    private static final long serialVersionUID = -801259325095408394L;
    
    private String idCard;
    private String name;
    private int age;
    
    public MyStudent(String idCard, String name, int age) {
        super();
        this.idCard = idCard;
        this.name = name;
        this.age = age;
    }
    
    public MyStudent(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public MyStudent() {
        super();
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

    public String getidCard() {
        return idCard;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setidCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "MyStudent [idCard=" + idCard + ", name=" + name + ", age=" + age + "]";
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}
