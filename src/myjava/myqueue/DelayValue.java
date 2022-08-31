package myjava.myqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class DelayValue implements Delayed {
    
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int compareTo(Delayed o) {
        // TODO Auto-generated method stub  
        if (this.time < ((DelayValue) o).time)
            return -1;
        else if (this.time > ((DelayValue) o).time)
            return 1;
        else
            return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long cost = time - System.nanoTime();
        long r = unit.convert(cost, TimeUnit.MICROSECONDS);
        System.out.println(cost + " " + r);
        return r;
    }
    
}
