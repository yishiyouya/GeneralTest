package mypattern.myeventlistener;

//�¼�����
public class EventObject extends java.util.EventObject {
    
    private static final long serialVersionUID = 1L;
    
    public EventObject(Object source) {
        super(source);
    }
    
    public void doEvent() {
        System.out.println("֪ͨһ���¼�Դ source :" + this.getSource());
    }

}
