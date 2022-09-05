package myjava.myio;

import scala.actors.threadpool.Arrays;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class MyTestFile {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        batchWriteFile();
    }


    /**
     * 批量生成多个文本文件，文件名 1.txt 2.txt 3.txt
     * @throws Exception
     */
    public static void batchWriteFile() throws Exception {
        String[] nums = new String[]{
                "111111111111111111111111111111111111111111111111111111111111111111111",
                "222222222222222222222222222222222222222222222222222222222222222222222",
                "333333333333333333333333333333333333333333333333333333333333333333333",
                "444444444444444444444444444444444444444444444444444444444444444444444"
        };
        int MAX_LENGTH = 3000;
        List<String> numLists = Arrays.asList(nums);
        for (String num : numLists) {
            List<String> singleNum = new ArrayList<>();
            for (int i = 0; i < MAX_LENGTH; i++) {
                singleNum.add(",".concat("" + i).concat(", ").concat(num));
            }
            testWriteFile(singleNum, num.substring(0, 1).concat(".txt"));
            System.out.format("finish:%s\n", num);
        }
    }
    public static void testWriteFileCost() {
        String[] lines = new String[]{"A、 B、 C、 D、 E、 F 、G 、H、 I、 J 、K、 L、 M 、N 、O、 P、 Q、 R、 S、 T 、U、 V、 W 、X 、Y、 Z "};
        long start = System.nanoTime();
        try {
            for (int i = 0; i < 1000; i++) {
                //testWriteFile(Arrays.asList(lines));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long cost = System.nanoTime() - start;
        System.err.println(cost);
    }
    
    public static void testSepPath() {
        String sepPath = File.separator;
        System.out.println(sepPath);
    }

    /**
     * 测试生成新文件
     */
    public static void testWrite() {
        List<String> list = new ArrayList<String>();
        list.add("A");
        try {
            long start = System.currentTimeMillis();
            testWriteFile(list, "result.log");
            long stop = System.currentTimeMillis();
            System.out.println(stop - start);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void getAbsPath(){
        try {
            File file = new File("");
            String projectPath = file.getAbsolutePath();
            String canonPath = file.getCanonicalPath();
            System.out.format("absolute:%s, \n canonPath:%s", projectPath, canonPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 读文件
     * @throws IOException 
     */
    public static void testReadFile() throws IOException {
        String filePath = "F:\\File\\编程实践\\瑞信方正API-支持全部业务的空调用\\API接口说明_test.md";
        // Java 8例子
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
    }
    
    /**
     * 写文件
     * @param collection
     * @param fileName 文件名
     * @throws Exception
     */
    public static <E> void testWriteFile(Collection<E> collection, String fileName) throws Exception {
        FileWriter fileWriter = null;
        try {
            File file = new File("");
            String resPath = file.getAbsoluteFile() + File.separator + fileName;
            File resFile = new File(resPath);
            if (!resFile.exists()) {
                resFile.createNewFile();
            } else {
                resFile = new File(resPath);
            }
            fileWriter = new FileWriter(resFile);
            Iterator<E> inter = collection.iterator();
            while (inter.hasNext()) {
                E e = (E) inter.next();
                fileWriter.write(String.valueOf(e) + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                fileWriter.close();
            }
        }
    }

}



