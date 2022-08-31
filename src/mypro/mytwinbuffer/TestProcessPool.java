package mypro.mytwinbuffer;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ���г�
 */
public class TestProcessPool extends LinkedBlockingQueue<Runnable> {

    private static final long serialVersionUID = 7844550791500228724L;

    //ִ�б�����г��޵ȴ����ʱ�䣨s��
    public final static int DEFUALT_OVEROW_SLEEP_TIME = 3;

    //ִ�б�����г������Դ���
    public final static int DEFUALT_OVEROW_REPEAT_TIMES = 3;

    /**
     * {@inheritDoc}
     * @see LinkedBlockingQueue
     */
    public TestProcessPool() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see LinkedBlockingQueue
     */
    public TestProcessPool(Collection<? extends Runnable> c) {
        super(c);
    }

    /**
     * {@inheritDoc}
     * @see LinkedBlockingQueue
     */
    public TestProcessPool(int capacity) {
        super(capacity);
    }

    /**
     * �����������޵Ŀ��ƣ������ǰ��������е���������������ڵ�ǰ����sleep 3�룬
     * <br> Ȼ���ٴμ���������е�����������3�Σ�ֱ�����Խ���������������������У�
     * <br> ���3�κ󣬶���������Ȼ�������ޣ��򷵻�false.
     */
    @Override
    public boolean offer(Runnable o) {
        boolean isOffer = false;
        try {
            isOffer = super.offer(o, DEFUALT_OVEROW_SLEEP_TIME, TimeUnit.SECONDS);

            int count = 1;
            while (!isOffer) {
                if (count == DEFUALT_OVEROW_REPEAT_TIMES) {
                    return false;
                }
                isOffer = super.offer(o, DEFUALT_OVEROW_SLEEP_TIME, TimeUnit.SECONDS);
                count++;
            }
        } catch (InterruptedException e) {
        }
        return isOffer;
    }

}
