package mywork.myalgoserver.mytimer;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 备份历史数据
 */
public class MySystemInitTask extends TimerTask {

    private static Logger log = LoggerFactory.getLogger(MySystemInitTask.class);

    public void run() {

        log.debug("begin data backup");
        
        long occurtime = System.currentTimeMillis();
        // 初始化derby数据库
        log.info("derby clear table cost :" + (System.currentTimeMillis() - occurtime));
        log.debug("end data backup");
    }
    
}
