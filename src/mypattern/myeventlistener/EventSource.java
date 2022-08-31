package mypattern.myeventlistener;

import java.util.Vector;

//�¼�Դ
public class EventSource {
    
    //�������б���������ע���������б�
    private Vector<EventListener> listenerList = new Vector<EventListener>();
    
    //ע�������
    public void addListener(EventListener eventListener) {
        listenerList.add(eventListener);
    }
    
    //����ע��
    public void removeListener(EventListener eventListener) {
        listenerList.remove(eventListener);
    }
    
    //�����ⲿ�¼�
    public void notifyListenerEvents(EventObject event) {
        for (EventListener eventListener : listenerList) {
            eventListener.handleEvent(event);
        }
    }

}
