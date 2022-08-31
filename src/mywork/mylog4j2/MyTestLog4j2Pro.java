package mywork.mylog4j2;
import java.io.IOException;

import mywork.mylog4j2.mymdslog.AsyncLogThread;
import mywork.mylog4j2.mymdslog.MDSLogThread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyTestLog4j2Pro {

    /*private static Logger log = LogManager.getLogger(MyTestLog4j2Pro.class);*/
    private static Logger log = null;
    
    private static MDSLogThread asyncLogThread = AsyncLogThread.getInstance();
    
    public static void main(String[] args) throws IOException {
        
        //loadFileLog4j2();
        
        mdsLog();
    }

    public static void loadFileLog4j2() throws IOException {
        log.info("hh");
        log.debug("hh");
        log.error("hh");
        log.fatal("hh");
    }
    
    
    public static void mdsLog() throws IOException {
        
        asyncLogThread.info("hh");
        asyncLogThread.debug("hh");
        asyncLogThread.error("hh");
    }
    
    
}
