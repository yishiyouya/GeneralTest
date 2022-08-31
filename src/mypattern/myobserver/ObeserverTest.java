package mypattern.myobserver;

import java.util.Observer;

/*
 * 
 */
public class ObeserverTest {
    public static void main(String[] args) {
        Watched watched = new Watched();
        WatcherDemo watcherDemo = new WatcherDemo();
        
        Observer observer = new CloseObserver();
        watched.addObserver(watcherDemo);
        watched.addObserver(observer);
        //�����򿪴����¼���֪ͨ�۲���
        watched.notifyObservers("openWindows");
        //�����رմ����¼���֪ͨ�۲���
        watched.notifyObservers("closeWindows");

    }
}
