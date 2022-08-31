package mywork.myalgoserver;

import java.math.BigDecimal;

import myjava.mylangs.mydate.MyTestDate;

public class MyInternalTime {

    //private static long orderStartTime = 20200615093200l; 
    private static long orderStartTime = 20200615094500l; 
    private static long amEndTime = 20200615113000l; 
    private static long pmStartTime = 20200615130000l; 
    
    public static void main(String[] args) {
        long milli = System.currentTimeMillis();
        long ocrTime = MyTradeDate.getLongDateMilliTime(milli);
        System.out.println(milli+"\n"+ocrTime);
        
        //orgiCalInternalTime();
        calInternalTime();
        
    }

    public static long getStartTime(){
        //long milli = MyTradeDate.getDateMillis(20200615091500l);// begin 11:30 end
        long milli = MyTradeDate.getDateMillis(orderStartTime);// 11:30 end+interval 13:00
        return milli;
    }
    
    
    /**
     * 考虑中午闭市时间
     */
    public static void calInternalTime() {
        int orderQty = 300;
        int minOrderQty = 100;
        long startTime = orderStartTime;
        long orderEndTime = 20200615145500l;
        long interruptTime = 0;
        long midTime = 0;
        long waitTime = 0;
        
        long time = MyTradeDate.getIntevalSeconds(orderEndTime, startTime) - midTime - interruptTime;
        int orderTimes = (int) (new BigDecimal(orderQty / minOrderQty).setScale(4,BigDecimal.ROUND_UP).doubleValue());
        
        if(orderTimes==0){
            orderTimes=1; 
        }
        // 计算实际下单间隔时间
        //int intervalTime = (int)((double)time / orderTimes * 1000);
        int intervalTime = (19*60+35)*1000;
        //int intervalTime = (2*60*60)*1000;
        
        // 计算下单耗时
        long occurTime = getStartTime();
        long beginTime = getStartTime();
        long nextTime = beginTime;
        long endTime = getStartTime();

        long nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
        while (nextTimeFmt < orderEndTime) {
            
            nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
            // 等待间隔时间
            waitTime = getWaitTime(intervalTime, beginTime, endTime, nextTime);
            occurTime += waitTime;
            beginTime = occurTime;
            nextTime = beginTime;
            endTime = beginTime + 3;
            
            
            // 下次开始时间
            nextTime = endTime + (intervalTime - (endTime - nextTime));
            
            
            if (waitTime > 0) {
                System.out.println("&&&&&&&&&strintervalTime^waitTime^beginTime^endTime^nextTime^(endTime - beginTime)————>\n\t "
                        +MyTradeDate.getFmtTime(intervalTime)+"^"+MyTradeDate.getFmtTime(waitTime)
                        +"^"+MyTradeDate.getLongDateTime(beginTime)
                        +"^"+MyTradeDate.getLongDateTime(endTime)
                        +"^"+MyTradeDate.getLongDateTime(nextTime)
                        +"^"+(endTime - beginTime)+"==========");
            }
            /*if (waitTime > 0) {
                System.out.println("&&&&&&&&&strintervalTime^waitTime^beginTime^endTime^nextTime^endTime - beginTime————>"
                        +MyTradeDate.getFmtTime(intervalTime)+"^"+MyTradeDate.getFmtTime(waitTime)
                        +"^"+MyTradeDate.getLongDateTime(beginTime)
                        +"^"+MyTradeDate.getLongDateTime(endTime)
                        +"^"+MyTradeDate.getLongDateTime(nextTime)
                        +"^"+(endTime - beginTime)+"==========");
            }*/
        }
        
    }
    
    /**
     * 获取等待时间
     * @param intervalTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long getWaitTime(long intervalTime, long beginTime, long endTime, long nextTime) {
        long waitTime = intervalTime;
        if ((MyTradeDate.getLongDateTime(endTime) < pmStartTime
                    && MyTradeDate.getLongDateTime(endTime) > amEndTime)
            || (endTime + intervalTime > MyTradeDate.getDateMillis(amEndTime) 
                    && endTime + intervalTime < MyTradeDate.getDateMillis(pmStartTime))
            ) {
            if (MyTradeDate.getLongDateTime(endTime) < pmStartTime
                    && MyTradeDate.getLongDateTime(endTime) > amEndTime) {
                
                System.out.println("1");
            }
            if (endTime + intervalTime > MyTradeDate.getDateMillis(amEndTime) 
                    && endTime + intervalTime < MyTradeDate.getDateMillis(pmStartTime)) {
                
                System.out.println("2");
            }
            System.out.println(MyTradeDate.getLongDateTime(endTime));
            System.out.println(endTime);
            System.out.println(intervalTime);
            System.out.println(endTime + intervalTime);
            System.out.println(MyTradeDate.getDateMillis(amEndTime));
            System.out.println(MyTradeDate.getDateMillis(pmStartTime));
            
            waitTime = intervalTime + MyTradeDate.getDateMillis(pmStartTime) - MyTradeDate.getDateMillis(amEndTime);
        } else if ((endTime > beginTime && endTime <= MyTradeDate.getDateMillis(amEndTime))
                || beginTime >= MyTradeDate.getDateMillis(pmStartTime)) {
            if (endTime - beginTime < intervalTime) {
                waitTime = nextTime - endTime;
            }
        } 
        return waitTime;
    }
    
    /**
     * 获取等待时间
     * @param intervalTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long getWaitTimeBak(long intervalTime, long beginTime, long endTime, long nextTime) {
        long waitTime = intervalTime;
        if ((MyTradeDate.getLongDateTime(beginTime) < amEndTime
                    && MyTradeDate.getLongDateTime(endTime) > amEndTime)
            || (beginTime < MyTradeDate.getDateMillis(amEndTime)
                    && endTime > MyTradeDate.getDateMillis(pmStartTime))
            || (beginTime + intervalTime > MyTradeDate.getDateMillis(amEndTime)
                    && beginTime + intervalTime < MyTradeDate.getDateMillis(pmStartTime))
            || (endTime + intervalTime > MyTradeDate.getDateMillis(amEndTime) 
                    && endTime + intervalTime < MyTradeDate.getDateMillis(pmStartTime))
            ) {
            
            waitTime = intervalTime + MyTradeDate.getDateMillis(pmStartTime) - MyTradeDate.getDateMillis(amEndTime);
        } else if ((endTime > beginTime && endTime <= MyTradeDate.getDateMillis(amEndTime))
                || beginTime >= MyTradeDate.getDateMillis(pmStartTime)) {
            if (endTime - beginTime < intervalTime) {
                waitTime = nextTime - endTime;
            }
        } 
        return waitTime;
    }
    
    /**
     *  TWAP_ADV_SWS_V1 原逻辑计算执行间隔
     */
    public static void orgiSwsCalInternalTime() {
        int orderQty = 2000;
        int minOrderQty = 100;
        long startTime = 20200615094600l;
        long orderEndTime = 20200615195100l;
        long interruptTime = 0;
        long midTime = 0;
        long waitTime = 0;
        
        
        long time = MyTradeDate.getIntevalSeconds(orderEndTime, startTime) - midTime - interruptTime;
        int orderTimes = (int) (new BigDecimal(orderQty / minOrderQty).setScale(4,BigDecimal.ROUND_UP).doubleValue());
        
        if(orderTimes==0){
            orderTimes=1; 
        }
        // 计算实际下单间隔时间
        int intervalTime = (int)((double)time / orderTimes * 1000);
        
        // 计算下单耗时
        long occurTime = System.currentTimeMillis();
        long beginTime = System.currentTimeMillis();
        long nextTime = beginTime;
        long endTime = System.currentTimeMillis();

        long nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
        while (nextTimeFmt < orderEndTime) {
            nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
            occurTime += waitTime;
            beginTime = occurTime + waitTime;
            endTime = beginTime + waitTime + 3;
            
            
            // 下次开始时间
            nextTime = endTime + (intervalTime - (endTime - nextTime));
            
            // 等待间隔时间
            
            waitTime = intervalTime;
            if (endTime - beginTime < intervalTime) {
                waitTime = nextTime - endTime;
            }
            if (waitTime > 0) {
                System.out.println("==========strintervalTime^waitTime^beginTime^endTime^nextTime^endTime - beginTime————>"
                        +MyTradeDate.getFmtTime(intervalTime)+"^"+MyTradeDate.getFmtTime(waitTime)
                        +"^"+MyTradeDate.getLongDateTime(beginTime)
                        +"^"+MyTradeDate.getLongDateTime(endTime)
                        +"^"+MyTradeDate.getLongDateTime(nextTime)
                        +"^"+(endTime - beginTime)+"==========");
            }
        }
        
    }
    
    /**
     *  TWAP_PLUS_V1 原逻辑计算执行间隔
     */
    public static void orgiCalInternalTime() {
        int orderQty = 2000;
        int minOrderQty = 100;
        long startTime = 20200615094600l;
        long orderEndTime = 20200615195100l;
        long interruptTime = 0;
        long midTime = 0;
        long waitTime = 0;
        
        
        long time = MyTradeDate.getIntevalSeconds(orderEndTime, startTime) - midTime - interruptTime;
        int orderTimes = (int) (new BigDecimal(orderQty / minOrderQty).setScale(4,BigDecimal.ROUND_UP).doubleValue());
        
        if(orderTimes==0){
            orderTimes=1; 
        }
        // 计算实际下单间隔时间
        int intervalTime = (int)((double)time / orderTimes * 1000);
        
        // 计算下单耗时
        long occurTime = System.currentTimeMillis();
        long beginTime = System.currentTimeMillis();
        long nextTime = beginTime;
        long endTime = System.currentTimeMillis();
        
        long nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
        while (nextTimeFmt < orderEndTime) {
            nextTimeFmt = MyTradeDate.getLongDateTime(nextTime);
            occurTime += waitTime;
            beginTime = occurTime + waitTime;
            endTime = beginTime + waitTime + 3;
            
            
            // 下次开始时间
            nextTime = endTime + (intervalTime - (endTime - nextTime));
            
            // 等待间隔时间
            
            waitTime = intervalTime;
            if (endTime - beginTime < intervalTime) {
                waitTime = nextTime - endTime;
            }
            if (waitTime > 0) {
                System.out.println("==========strintervalTime^waitTime^beginTime^endTime^nextTime^endTime - beginTime————>"
                        +MyTradeDate.getFmtTime(intervalTime)+"^"+MyTradeDate.getFmtTime(waitTime)
                        +"^"+MyTradeDate.getLongDateTime(beginTime)
                        +"^"+MyTradeDate.getLongDateTime(endTime)
                        +"^"+MyTradeDate.getLongDateTime(nextTime)
                        +"^"+(endTime - beginTime)+"==========");
            }
        }
        
    }

}
