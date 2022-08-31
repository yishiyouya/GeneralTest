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

            //�ͷ������ڴ�
            if (reqList != null) {
                reqList.clear();
                reqList = null;
            }

            //������ɺ���һ�ε����߳�
            if (twin != null)
                twin.wakeup();
        }
    }

    /**
     * ������
     * @param reqList
     */
    protected abstract void execute(Collection list);
}
