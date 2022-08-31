package mypattern.myeventlistener;

public class CloseEventListener implements EventListener {
    
    private EventObject event;
    
    public CloseEventListener(EventObject event) {
        super();
        this.event = event;
    }

    @Override
    public void handleEvent(EventObject event) {
        event.doEvent();
        if (event.getSource().equals("closeWindows")) {
            System.out.println("doClose");
        }
    }
}
