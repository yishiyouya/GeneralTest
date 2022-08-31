package mywork.myjavaapi.anaStockOrder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import myutil.ParaChecker;
import myutil.StringUtil;

import edu.emory.mathcs.backport.java.util.TreeMap;
import myjava.mylangs.mydate.MyTestDate;
import mywork.myjavaapi.MyFileUtil;

public class MyStockOrderAnaLog {

    /**
     * 根据 requestId ，取 messageReceived 、 take from queue、messageSent时间。
     */
    
    /**
     * time=2019-11-27 14:55:20.809, RemoteAddress=/18.1.178.67:58444, messageReceived={"head": {"msgType": 15002, "sessionId": "SID_1000000001", "requestId": 10348592}, "request": {"orderCancelInfo": {"acctId": "000000000109", "exchId": "0", "contractNum": "1000267978"}}}
     * 
     * time=2019-11-27 14:55:20.809, RemoteAddress=/18.1.178.67:58444, take from queue=10348592,{"orderCancelInfo":{"exchId":"0","acctId":"000000000109","contractNum":"1000267978"}}
     * 
     * time=2019-11-27 14:55:20.824, RemoteAddress=/18.1.178.67:58444, messageSent={"head":{"msgType":15002,"requestId":10348592,"sessionId":"SID_1000000001"},"respond":{"errorCode":"","errorMsg":"","lastFlag":true,"successFlg":0},"data":{"completeNum":1,"orderTime":"2019-11-27 14:55:20","contractNum":"1000267978"}}
     * 
     */
    
    private static final String filePath = "F:\\File\\编程实践\\Python下单再收盘集合竞价期间，有三分钟延时\\请求统计用时";
    private static final String logPath = "F:\\File\\编程实践\\Python下单再收盘集合竞价期间，有三分钟延时\\FZZQ_Pythonlog_20191127";
    private static final String logType = "comm_server";
//    private static final String logPath = "F:\\File\\编程实践\\Python下单再收盘集合竞价期间，有三分钟延时\\FZZQ_PYTHON_20191202";
//    private static final String logType = "comm_server_20191202_";
    
//    private static final String logPath = "E:\\lunaWorkSpace\\fix_client_gateway\\RN_API_Server\\log";
//    private static final String logType = "comm_server_2019";
//    private static final String filePath = "E:\\lunaWorkSpace\\fix_client_gateway\\RN_API_Server\\请求统计用时";
    
    private static final String charSet = "UTF-8";
    private static final String reqId = "\"requestId\":";
    private static final String msgType = "\"msgType\":";
    private static final String msgRec = "messageReceived=";//"messageReceived={"head": {"msgType": 15002, "sessionId": "SID_1000000001", "requestId": 10348592},"
    private static final String tfque = "take from queue=";//"take from queue=10348592,"
    private static final String msgSen = "messageSent=";//"messageSent={"head":{"msgType":15002,"requestId":10348592,"
    private static final String time = "time=";
    
    private static Map<String, ReqValue> reqMap = new HashMap<String, ReqValue>();
    
    public static void main(String[] args) {
        try {
            anaStockOrderByMsgType1127();
            anaStockOrderByReqId1127();
            //anaStockOrderByReqId1202();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void unitTest() throws IOException {
        MyFileUtil.readFile(logPath, logType, charSet);
        List<String> fileLines = MyFileUtil.getFileLines();
        for (String line : fileLines) {
            System.out.println(getTime(line));
        }
    }
    
    /**
     * 按msgType统计 messageSentTime
     * @throws IOException
     */
    public static void anaStockOrderByMsgType1127() throws IOException {
        MyFileUtil.readFile(logPath, logType, charSet);
        List<String> fileLines = MyFileUtil.getFileLines();
        System.out.println(fileLines.size());
        for (String line : fileLines) {
            setLineMsgTypeTime(line);
        }
        printMap(reqMap, new File(filePath+logType+"_msgType.txt"));
        reqMap.clear();
    }
    
    /**
     * 按 requestId 统计 messageReceived，take from queue，messageSent Time
     * @throws IOException
     */
    public static void anaStockOrderByReqId1127() throws IOException {
        MyFileUtil.readFile(logPath, logType, charSet);
        List<String> fileLines = MyFileUtil.getFileLines();
        System.out.println(fileLines.size());
        for (String line : fileLines) {
            setLineRequestIdTime(line);
        }
        printMap(reqMap, new File(filePath+logType+"_requestId.txt"));
        reqMap.clear();
    }
    
    public static void anaStockOrderByReqId1202() throws IOException {
        for (int i = 8; i < 16; i++) {
            String newlogType = logType+(i < 10 ? "0"+i : i);
            MyFileUtil.readFile(logPath, newlogType, charSet);
            List<String> fileLines = MyFileUtil.getFileLines();
            System.out.println(fileLines.size());
            for (String line : fileLines) {
                setLineRequestIdTime(line);
            }
            printMap(reqMap, new File(filePath+newlogType+".txt"));
            reqMap.clear();
        }
    }
    
    public static void printMap(Map<String, ReqValue> reqMap, File file) throws IOException {
        List<String> reqIds = new ArrayList<String>();
        List<String> lines = new ArrayList<String>();
        for (Entry<String, ReqValue> req : reqMap.entrySet()) {
            reqIds.add(req.getKey());
        }
        Collections.sort(reqIds);
        for (String reqId : reqIds) {
            lines.add(reqMap.get(reqId).toString());
            System.out.println(reqMap.get(reqId));
        }
        MyFileUtil.writeFile(file, lines, charSet);
    }
    
    public static void setLineRequestIdTime(String line){
        String requestId = getLineRequestId(line);
        String msgType = getLineMsgType(line);
        ReqValue rValue = reqMap.get(requestId);
        if (null == rValue) {
            rValue = new ReqValue();
        }
        rValue.setReqId(requestId);
        rValue.setMsgType(msgType);
        if (ParaChecker.isValidPara(getRequestId(line, reqId, msgRec))) {
            rValue.setRecTime(getTime(line));
        }
        if (ParaChecker.isValidPara(getRequestId(line, tfque, tfque))) {
            rValue.setTakTime(getTime(line));
        }
        if (ParaChecker.isValidPara(getRequestId(line, reqId, msgSen))) {
            rValue.setSentTime(getTime(line));
        }
        reqMap.put(requestId, rValue);
    }
    
    public static void setLineMsgTypeTime(String line){
        String requestId = getLineRequestId(line);
        String msgType = getLineMsgType(line);
        String sentTime = "";
        if(ParaChecker.isValidPara(getRequestId(line, reqId, msgSen))) {
            sentTime = getTime(line);
        }
            
        ReqValue rValue = reqMap.get(msgType+sentTime);
        if (null == rValue) {
            rValue = new ReqValue();
        }
        //ReqValue rValue = new ReqValue();
        rValue.setReqId(requestId);
        rValue.setMsgType(msgType);
        if (ParaChecker.isValidPara(getRequestId(line, reqId, msgRec))) {
            rValue.setRecTime(getTime(line));
        }
        if (ParaChecker.isValidPara(getRequestId(line, tfque, tfque))) {
            rValue.setTakTime(getTime(line));
        }
        if (ParaChecker.isValidPara(getRequestId(line, reqId, msgSen))) {
            rValue.setSentTime(getTime(line));
        }
        reqMap.put(msgType+rValue.getSentTime(), rValue);
    }
    
    public static String getLineRequestId(String line){
        return getRequestId(line, reqId, msgRec)+getRequestId(line, tfque, tfque)+getRequestId(line, reqId, msgSen);
    }
    
    public static String getLineMsgType(String line){
        return getRequestId(line, msgType, msgRec)+getRequestId(line, msgType, msgSen);
    }
    
    public static String getRecRequestId(String line){
        return getRequestId(line, reqId, msgRec);
    }
    
    public static String getTakeRequestId(String line){
        return getRequestId(line, tfque, tfque);
    }
    
    public static String getSenRequestId(String line){
        return getRequestId(line, reqId, msgSen);
    }
    
    public static String getRequestId(String line, String reqId, String reqMType){
        String requestId = "";
        String lineTmp = "";
        if (line.indexOf(reqMType) > -1) {
            lineTmp = line.substring(line.indexOf(reqId)+reqId.length(), line.length());
            requestId = lineTmp.substring(0, lineTmp.indexOf(","));
            if (ParaChecker.isValidPara(requestId)) {
                requestId = requestId.replaceAll("}", "").replaceAll(" ", "");
            }
        }
        return requestId;
    }
    
    public static String getTime(String line){
        return line.substring(line.indexOf(time)+time.length(), line.indexOf(","));
    }
    
}

class ReqValue {
    private String reqId;
    private String msgType;
    private String recTime;
    private String takTime;
    private String sentTime;
    public String getReqId() {
        return reqId;
    }
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getRecTime() {
        return recTime;
    }
    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }
    public String getTakTime() {
        return takTime;
    }
    public void setTakTime(String takTime) {
        this.takTime = takTime;
    }
    public String getSentTime() {
        return sentTime;
    }
    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }
    @Override
    public String toString() {
        return "ReqValue [reqId="
            + reqId
            + ", msgType="
            + msgType
            + ", recTime="
            + recTime
            + ", takTime="
            + takTime
            + ", costTime="
            + ", sentTime="
            + sentTime
            + "]";
    }
    
    
    
}

