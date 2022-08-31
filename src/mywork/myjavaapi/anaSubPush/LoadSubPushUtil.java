package mywork.myjavaapi.anaSubPush;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myutil.ParaChecker;
import myutil.StringUtil;

import mywork.myjavaapi.MyDataPersisUtil;
import mywork.myjavaapi.MyFileUtil;
import mywork.mymysql.MyConst;


/**
 * 加载subscript_push.log到MongoDB
 */
public class LoadSubPushUtil {
    
    private static Logger log = LoggerFactory.getLogger(LoadSubPushUtil.class);
    
    private static String msgPros = "【Message pros】";
    private static String acctIdPre = "  acctId=";
    private static String clientSerialNumPre = "  clientSerialNum=";
    private static String senderSubIdPre = "  senderSubId=";
    private static String msgData = "【Message data】";
    private static int msgDNum = 5;//【Message data】数据行数
    private static int msgDStart = 0;//【Message data】数据行开始
    private static int msgDend = 0;//【Message data】数据行结束
    public static String sep = "======";//【Message data】数据行结束
    
    private static List<String> lines = new ArrayList<String>(); 
    
    public static void main(String[] args) throws Exception {
        String path = "F:\\File\\编程实践\\中信生产 订阅失效\\log\\";
        String fileType = "subscript_20200103";
        String charSet = "GBK";
        readSubPush(path, fileType, charSet);
        List<SubPushValue> subPushs = lineToSubPush(lines);
        String tableName = "subscriptpush_zx";
        persisToMysql(subPushs, tableName);
        log.info("load compeleted");
    }
    
    /**
     * 持久数据到数据库
     * @param subPushs
     * @throws Exception
     */
    public static void persisToMysql(List<SubPushValue> subPushs, String tableName) throws Exception {
        String sql =
            "INSERT INTO " + tableName + " (acctId, clientSerialNum, senderSubId, messageData1, messageData2, messageData3) "
            + " VALUES (?, ?, ?, ?, ?, ?)";
        MyDataPersisUtil.insert(MyConst.DB_MYSQL, MyConst.KIND_SUB_PUSH, sql, subPushs);
    }
    
    /**
     * 加载日志
     * @param path
     * @param fileType
     * @param charSet
     * @return
     * @throws IOException
     */
    private static List<String>  readSubPush(String path, String fileType, String charSet) throws IOException{
        MyFileUtil.readFile(path, fileType, charSet);
        lines = MyFileUtil.getFileLines();
        return lines;
    }
    
    /**
     * 解析文件到SubPushValue
     * @param lines
     * @return
     */
    public static List<SubPushValue> lineToSubPush(List<String> lines) {
        List<SubPushValue> subPushs = new ArrayList<SubPushValue>(); 
        String acctId = "";
        String clientSerialNum = "";
        String senderSubId = "";
        String messageData = "";
        String preLine = "";
        SubPushValue subPushValue = null;
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            if (preLine.startsWith(msgPros)) {
                subPushValue = new SubPushValue();
            }
            if (lines.get(i).startsWith(acctIdPre)) {
                acctId = lines.get(i).substring(lines.get(i).indexOf("\'")+1, lines.get(i).length() - 1);
            }
            if (lines.get(i).startsWith(clientSerialNumPre)) {
                clientSerialNum = lines.get(i).substring(lines.get(i).indexOf("\'")+1, lines.get(i).length() - 1);
            }
            if (lines.get(i).startsWith(senderSubIdPre)) {
                senderSubId = lines.get(i).substring(lines.get(i).indexOf("\'")+1, lines.get(i).length() - 1);
            }
            if (preLine.startsWith(msgData)) {
                messageData = "";
                msgDStart = i;
                msgDend = i + msgDNum;
                for (int j = msgDStart; j < msgDend; j++) {
                    messageData += lines.get(j);
                    messageData += sep;
                }
            }
            if (ParaChecker.isValidPara(acctId)
                    && ParaChecker.isValidPara(clientSerialNum)
                    && ParaChecker.isValidPara(senderSubId)
                    && ParaChecker.isValidPara(messageData)
                    && i >= msgDend) {
                subPushValue = new SubPushValue(acctId, clientSerialNum, senderSubId, messageData);
                subPushs.add(subPushValue);
                msgDStart = 0;
                msgDend = 0;
            }
            preLine = lines.get(i);
        }
        return subPushs;
    }
    
    
    //数据持久化
    public static void dataPersis() {
        
    }
    
    
}
