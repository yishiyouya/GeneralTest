package mywork.myjavaapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MyFileUtil {
    
    private static List<String> fileLines = new ArrayList<String>();
    
    public static List<String> getFileLines() {
        return fileLines;
    }
    
    public static void clearFileLines() {
        fileLines.clear();
    }
    
    
    public static void readFile(String path, String logType, String charSet) throws IOException {
        File file = new File(path);
        File[] fileList = file.listFiles();
        fileLines.clear();
        for (File fl : fileList) {
            if (fl.getName().indexOf(logType) > -1) {
                fileLines.addAll(FileUtils.readLines(fl, charSet));
            }
        }
    }
    
    public static void writeFile(String filepath, List<String> files, String charSet) throws IOException {
        File file = new File(filepath);
        FileUtils.writeLines(file, files);
    }
    
    public static void writeFile(File file, List<String> files, String charSet) throws IOException {
        FileUtils.writeLines(file, files);
    }
    
    
}
