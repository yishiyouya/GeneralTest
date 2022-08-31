package myjava.mytest.test.pojo;

public class Student {
	protected int age = -1;
	private String name;
	private String address = calAddress();
	
	public Student(String name, int age) {
        super();
        this.age = age;
        this.name = name;
    }

	public String calAddress() {
	    return "hh";
	}
	
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student() {
		super();
	}

    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + ", address=" + address + "]";
    }
	
    
    
}
