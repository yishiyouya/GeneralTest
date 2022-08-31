package mywork.myapiserver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import myutil.ParaChecker;

public class ApiServerFileUtil {
    
    protected static final String sep = "@";
    
    public static List<File> getFiles(String filePath, String fileter) {
        List<File> fileList = new ArrayList<File>();
        File filePFile = new File(filePath);
        if (filePFile.isDirectory()) {
            File[] fileArr = filePFile.listFiles();
            for (File file : fileArr) {
                if (file.getName().indexOf(fileter) > -1) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
    
    public static void searchFunc(List<File> inputFile, String outFile, String func){
        FileWriter fw = null;
        try {
            //fw = new FileWriter(outFile, true);
            fw = new FileWriter(outFile);
            if (ParaChecker.isValidPara(func)) {
                for (File file : inputFile) {
                    BufferedReader in = getFileBuffer(file);
                    largeFileIO(in, fw, func);
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 根据func搜索in，结果输出到outFile
     * @param in
     * @param outFile
     * @param func
     */
    public static void largeFileIO(BufferedReader in, FileWriter fw, String func) {
        String[] funcArr = null;
        if (func.indexOf(sep) > -1) {
            funcArr = func.split(sep);
        }
        try {
            while (in.ready()) {
                String line = in.readLine();
                if (ParaChecker.isValidPara(line)) {
                    boolean contain = true;
                    if (null != funcArr) {
                        for (String fun : funcArr) {
                            if (line.indexOf(fun) < 0) {
                                contain = contain && false;
                                break;
                            }
                        }
                        if (contain) {
                            fw.append(line + System.lineSeparator());
                        }
                    } else if(line.indexOf(func) > -1){
                        fw.append(line + System.lineSeparator());
                    }
                }
            }
            fw.flush();
        } catch (IOException ex) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
    }
    
    
    /**
     * 获取输入流
     * @param inputFile
     * @return
     */
    @SuppressWarnings("unused")
    public static BufferedReader getFileBuffer(File inputFile) {
        BufferedInputStream bis = null;
        BufferedReader in = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(inputFile));
            in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);//10M缓存
        } catch (IOException ex) {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
        return in;
    }
}
