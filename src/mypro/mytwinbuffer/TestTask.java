package mypro.mytwinbuffer;

import java.util.Collection;

public abstract class TestTask implements Runnable {

    private TestTwinBufferProcessor twin = null;
    private Collection reqList = null;

    public TestTask(TestTwinBufferProcessor twin, Collection reqList) {
        this.twin = twin;
        this.reqList = reqList;
    }

    public void run() {

        try {
            execute(reqList);

        } catch (Throwable e) {

        } finally {

            //释放任务内存
            if (reqList != null) {
                reqList.clear();
                reqList = null;
            }

            //任务完成后唤醒一次调度线程
            if (twin != null)
                twin.wakeup();
        }
    }

    /**
     * 任务处理
     * @param reqList
     */
    protected abstract void execute(Collection list);
}
