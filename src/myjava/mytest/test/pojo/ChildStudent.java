package myjava.mytest.test.pojo;

public class ChildStudent extends Student{
	private String eysColor;
	
	public ChildStudent(String eysColor) {
        super();
        this.eysColor = eysColor;
    }
	
	public String getEysColor() {
        return eysColor;
    }

    public void setEysColor(String eysColor) {
        this.eysColor = eysColor;
    }

    public String toString() {
		return super.toString() + ", eysColor:" + this.eysColor;
	}
	
}
