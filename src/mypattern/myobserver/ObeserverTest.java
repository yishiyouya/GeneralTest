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
        //触发打开窗口事件，通知观察者
        watched.notifyObservers("openWindows");
        //触发关闭窗口事件，通知观察者
        watched.notifyObservers("closeWindows");

    }
}
