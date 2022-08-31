package mywork.myjavaapi.anaQuoSub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.saxon.functions.ConstantFunction.True;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;




public class MyQuoSubAnaLog {

    private static final String charSetU = "UTF-8";
    private static final String charSetG = "GBK";
    private static final String timeSep = "time=";
    private static final String pubSep = "����������:";
    private static List<String> fileLines = new ArrayList<String>();
    private static Map<String, Integer> quoAnaMap = new LinkedHashMap<String, Integer>();
    
    
    public static void main(String[] args) throws IOException {
        String logPath = "";
        String logType = "";
        String logPath1 = "";
        String logType1 = "";
        
        //apiServer��־
        logPath = "F:\\File\\���ʵ��\\����֤ȯ����pythonserver�����������ӳ�\\log_����api�����ӳ�";
        logPath = "F:\\File\\���ʵ��\\C# API ��Ȩ�µ� �Ͽ����� java_server";
        logType = "comm_server";
        //MDS��־
        logPath1 = "F:\\File\\���ʵ��\\����֤ȯ����pythonserver�����������ӳ�\\mdsLog";
        logType1 = "MDServer";
        readFile(logPath, logType, charSetU);
        
        analyApiServerFile(fileLines);
        
        /*
        readFile(logPath1, logType1, charSetG);
        analyMDSServerFile(fileLines);*/
        
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
    
    /**
     * Api Server��־������ÿ���Ӷ��ĵ�������
     * @param flLines
     */
    public static void analyApiServerFile(List<String> flLines){
        String timeKey = "";
        String oldTimeKey = "";
        int count = 0;
        for (String line : flLines) {
            timeKey = line.substring(line.indexOf(timeSep)+timeSep.length(), line.indexOf(",")-4);
            if (!timeKey.equals(oldTimeKey)) {
                count = 0;
            }
            oldTimeKey = timeKey;
            quoAnaMap.put(timeKey, ++count);
        }
        for (Entry<String, Integer> quoEntry : quoAnaMap.entrySet()) {
            System.out.format("%s %s\n", quoEntry.getKey(), quoEntry.getValue());
        }
    }
    
    /**
     * MDSServerÿ���ӷ��͵�������
     * @param flLines
     */
    public static void analyMDSServerFile(List<String> flLines) {
        String timeKey = "";
        String oldTimeKey = "";
        int count = 0;
        int eachNum = 0;
        String stkId = "";
        //String line = "2019-11-15 10:19:23.511|C=SZQuotV5|M=QuotV5|L=Info|T=31|��MQ�������ݳɹ�,����������:12,topic=SZ5_Stock_Quotation,";
        for (String line : flLines) {
            if (line.indexOf(".") > -1 && line.indexOf(pubSep) > -1) {
                timeKey = line.substring(0, line.indexOf("."));
                if (!timeKey.equals(oldTimeKey)) {
                    count = 0;
                }
                oldTimeKey = timeKey;
                line = line.replace("��MQ�������ݳɹ�,", "");
                /*eachNum = Integer.parseInt(line.substring(line.indexOf(pubSep)+pubSep.length(), line.indexOf(",")));
                count += eachNum;*/
                stkId = line.substring(line.indexOf("stkId,")+"stkId, ".length(), line.indexOf("stkId,")+"stkId,".length()+7);
                if (SubStkUtil.isSubStkIds(stkId)) {
                    quoAnaMap.put(timeKey, ++count);
                }
            }
        }
        for (Entry<String, Integer> quoEntry : quoAnaMap.entrySet()) {
            System.out.format("%s %s\n", quoEntry.getKey(), quoEntry.getValue());
        }
    }
    
    
    
    

}
