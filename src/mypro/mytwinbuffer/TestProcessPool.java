package mypro.mytwinbuffer;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列池
 */
public class TestProcessPool extends LinkedBlockingQueue<Runnable> {

    private static final long serialVersionUID = 7844550791500228724L;

    //执行报告队列超限等待间隔时间（s）
    public final static int DEFUALT_OVEROW_SLEEP_TIME = 3;

    //执行报告队列超限重试次数
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
     * 具有容量上限的控制，如果当前待处理队列的容量已满，则对于当前请求sleep 3秒，
     * <br> 然后再次检查待处理队列的容量，反复3次，直至可以将本次请求放入待处理队列中，
     * <br> 如果3次后，队列容量依然到达上限，则返回false.
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
