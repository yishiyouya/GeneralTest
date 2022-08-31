package mypattern.myobserver;

import java.util.Observable;
import java.util.Observer;

public class CloseObserver implements Observer{
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg.toString().equals("closeWindows")){
            System.out.println("已经关闭窗口");
        }
    }
    
}
