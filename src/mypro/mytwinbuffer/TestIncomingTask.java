package mypro.mytwinbuffer;

import java.util.Collection;

public class TestIncomingTask extends TestTask{

    long start = System.currentTimeMillis();
    long stop = 0l;
    
    public TestIncomingTask(TestTwinBufferProcessor twin, Collection reqList) {
        super(twin, reqList);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void execute(Collection list) {
        
    }

}
