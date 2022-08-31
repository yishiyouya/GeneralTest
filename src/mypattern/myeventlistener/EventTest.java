package mypattern.myeventlistener;

/**
 * 事件监听模式
 * 什么是事件监听器
 * 监听器将监听自己感兴趣的事件一旦该事件被触发或改变，立即得到通知，做出响应。例如：android程序中的Button事件。

 * java的事件监听机制可概括为3点：

 * java的事件监听机制涉及到 事件源，事件监听器，事件对象 三个组件。
 * 事件源管理监听器列表；
 * 事件监听器一般是接口，用来约定调用方式当事件源对象上发生操作时，它将会调用事件监听器的一个方法，并在调用该方法时传递事件对象过去
 * 事件监听器实现类,通常是由开发人员编写，开发人员通过事件对象拿到事件源，从而对事件源上的操作进行处理；
 * 事件对象对事件源上的操作进行处理。
 *
 */
public class EventTest {
    
    public static void main(String[] args) {
        
        EventSource eventSource = new EventSource();

        EventObject event = new EventObject("closeWindows");
        EventListener eventListener = new CloseEventListener(event);
        eventSource.addListener(eventListener);

        /*
         * 传入openWindows事件，通知listener，事件监听器，
         * 对open事件感兴趣的listener将会执行
         *
         */
        eventSource.notifyListenerEvents(event);
        
    }
}
