package myjava.mytest.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 * 读写文件
 * @author admin
 *
 */
public class FileUtil {
    public static void main(String[] args) {
        String path = "configure/database.properties";
        getProperties(path);
    }
    
    
    //读取properties文件
    public static Properties getProperties(String proPath){
        Properties pros = new Properties();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(proPath)));
            pros.load(inputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pros;
    }
    
}
