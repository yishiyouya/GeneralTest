
package mywork.myregex;

public class SubBean {
    private int index;
    private String sub1;
    private String sub2;
    private String sub3;
    
    public SubBean(int index, String sub1, String sub2) {
        super();
        this.index = index;
        this.sub1 = sub1;
        this.sub2 = sub2;
    }
    
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getSub1() {
        return sub1;
    }
    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }
    public String getSub2() {
        return sub2;
    }
    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }
    public String getSub3() {
        return sub3;
    }
    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }
    @Override
    public String toString() {
        return "SubBean [index="
            + index
            + ", sub1="
            + sub1
            + ", sub2="
            + sub2
            + ", sub3="
            + sub3
            + "]";
    }
}
