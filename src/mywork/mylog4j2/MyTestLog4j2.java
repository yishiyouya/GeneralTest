package mywork.mylog4j2;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class MyTestLog4j2 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //initLog4j2();
        
        loadFileLog4j2();
    }
    
    public static void initLog4j2(){

        Logger log = LogManager.getLogger(MyTestLog4j2.class);
        
        log.debug("hh");
        log.fatal("hh");
        
    }
    
    public static void loadFileLog4j2() throws IOException {
        File file = new File("E:/lunaWorkSpace/fix_client_gateway/GeneralTest/configuration/log4j2.xml");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        final ConfigurationSource source = new ConfigurationSource(in);
        Configurator.initialize(null, source);
        
        Logger log = LogManager.getLogger(MyTestLog4j2.class);
        
        log.info("hh");
        log.debug("hh");
        log.error("hh");
        log.fatal("hh");
    }
    
    public static void initLog4j2(String before){

        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File file = new File("E://lunaWorkSpace//fix_client_gateway//GeneralTest//configuration//log4j2_client.xml");

        // this will force a reconfiguration
        context.setConfigLocation(file.toURI());
        Configurator.setRootLevel(Level.DEBUG);
        
        Logger log = LogManager.getLogger(MyTestLog4j2.class);
        
        log.debug("hh");
        log.fatal("hh");
        
    }
    
}
