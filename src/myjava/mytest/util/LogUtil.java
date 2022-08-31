package myjava.mytest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {
    
    private static String logPath = "D:\\localTest\\log";
    
    public static Log log = LogFactory.getLog(LogUtil.class);
        
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //log("12", Constants.LOG_FILE_FAIL_TYPE);
        try {
            printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(getStackTrace(e));
        }
    }
    
    public static void printStackTrace() throws Exception{
        try {
            int i = 1/0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Throwable th = new Throwable(e.getCause());
            throw new Exception(th);
        } finally{
            System.out.println("finally");
        }
    }
    
    public static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    public static void log4jTest(){
        String test="sdibt";
        log.info("this is info:"+test);
        log.error("this is error:"+test);
        log.debug("this is debug:"+test);
    }
    
    //1.“Ï≥£–¥»’÷æ
    public static void log(String content, String typeName){
        FileOutputStream fos = null;
        boolean isAppend = true;
        String charsetName = "utf-8";
        content = new StringBuffer().append(Utils.getLocalHostLANAddress())
                .append(": ").append(content).append("\n").toString();
        File file = new File(logPath);
        if(!file.exists()){
            file.mkdir();
        }
        String fileName = logPath + File.separator + typeName 
                            + Utils.getCurDate() + ".log";
        file = new File(fileName);
        try {
            fos = new FileOutputStream(file, isAppend);
            byte[] con = content.getBytes(charsetName);
            fos.write(con);
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            try {
                fos.flush();
                fos.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("close fos exception!");
            }
        }
    }
    
    
}
