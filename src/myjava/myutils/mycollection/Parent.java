package myjava.myutils.mycollection;

public class Parent {
    private String name;
    private int age;
    protected boolean isMale = true;
    
    public Parent() {
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
    
    public boolean isMale() {
        return isMale;
    }
    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }
    @Override
    public String toString() {
        return "Parent [name=" + name + ", age=" + age + "]";
    }
}
