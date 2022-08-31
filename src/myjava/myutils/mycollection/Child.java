package myjava.myutils.mycollection;

public class Child extends Parent{
    private String title;

    public Child() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Child(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMale(boolean isFlag) {
        isMale = isFlag;
    }
    
    @Override
    public String toString() {
        return "Child [name=" + super.getName() + ", age=" + super.getAge() + ", title=" + title + "]";
    }
}
