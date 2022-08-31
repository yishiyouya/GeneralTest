package mypattern.myeventlistener;

//监听器接口
public interface EventListener extends java.util.EventListener {
    
    //事件处理
    public void handleEvent(EventObject event);
    
}
