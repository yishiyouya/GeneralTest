package mypro.mytwinbuffer;

import java.util.ArrayList;
import java.util.Collection;




public class TestTwinBufferService extends TestTwinBufferProcessor {

    private static TestTwinBufferService instance = null;

    public TestTwinBufferService(int bufferSize, int getSize, int poolSize, int threadSize) {
        super(bufferSize, getSize, poolSize, threadSize);
        this.setName(this.getClass().getSimpleName());
        this.start();
    }
    
    public static TestTwinBufferService getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (TestTwinBufferService.class) {

            if (instance == null) {
                //创建处理器
                instance = new TestTwinBufferService(10000, 5, 10000, 1);
            }
            return instance;
        }
    }
    
    @Override
    protected Collection handle(TestTwinBufferProcessor twin, Collection requestsList) {
        // TODO Auto-generated method stub
        //生成Runnable任务
        Collection tasks = new ArrayList();
        for (Object object : requestsList) {
            TestIncomingTask tTask = new TestIncomingTask(twin, requestsList);
            tasks.add(tTask);
        }
        
        return tasks;
    }

}
