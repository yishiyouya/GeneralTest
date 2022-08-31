package mywork.myauto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoCreateFile {

    private static String taskFile = "F:\\File\\编程实践";
    private static String txtType = ".txt";
    private static String sqlType = ".sql";
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] newTasks = {
            "PYTHONAPI-支持额度占用查询接口"
            };
        for (String task : newTasks) {
            autoCreFile(task);
        }
        System.out.println("create ok!");
    }
    
    //自动按照输入生成“F:\File\编程实践”目录及txt、sql
    public static void autoCreFile(String taskName) {
        String filePath = taskFile + File.separator + taskName;
        String txtName = filePath + File.separator + taskName + txtType;
        String sqlName = filePath + File.separator + taskName + sqlType;
        String tempName = filePath + File.separator + "temp" + txtType;
        String testName = filePath + File.separator + "test" + txtType;
        List<String> fileList = new ArrayList<String>();
        fileList.add(filePath);
        fileList.add(txtName);
        fileList.add(sqlName);
        fileList.add(tempName);
        fileList.add(testName);
        try {
            creFile(fileList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void creFile(List<String> fileList) throws IOException {
        for (int i = 0; i < fileList.size() ; i++) {
            File file = new File(fileList.get(i));
            if (i == 0) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
            System.out.println("suc create: " + fileList.get(i));
        }
    }
}
