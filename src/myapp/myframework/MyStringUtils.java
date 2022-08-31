package myapp.myframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MyStringUtils {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        indexOfNull();
    }
    
    
    public static void indexOfNull(){
        String a = "abc";
        int idx = a.indexOf("");
        System.out.println(idx);
        System.out.println(Arrays.asList(a.split("")));
    }
    
    public static void stringJoiner(){
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        String res = String.join(":", list);
        System.out.println(res);
    }
    
    public static void stringDulToken(){
        String transedType = "OH";
        String terTypeCotent = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL;OH:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL";
        StringTokenizer stType = new StringTokenizer(terTypeCotent.trim(), ";");
        boolean containType = false;
        while (stType.hasMoreTokens()) {
            String ct = stType.nextToken();
            containType = containType || ct.startsWith(transedType);
            if (ct.startsWith(transedType)) {
                containType = true;
                terTypeCotent = ct;
            }
        }
        
        System.out.println(containType  + " " +transedType + " " + terTypeCotent);
    }
    
    public static void stringToken(){
        String str = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL";
        StringTokenizer st = new StringTokenizer(str, "^");
        while (st.hasMoreTokens()) {
            String ct = st.nextToken();
            if (ct.indexOf("*") > -1) {
                System.out.println(ct);
            }
        }
    }
    
    public static void replaceAllTest() {
        /*String str = "PC; IIP: NA;LIP:UNKOWN; MAC:1831BF4BC3AB;hh:";  
        String regex = "(?<=:)[^:\\;]+(?=\\;)";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        System.out.println("replace: "+str.replaceAll(regex, "O"));  
        while (matcher.find()) {  
            if ("UNKOWN".equals(matcher.group(0))) {
                str = str.replace("UNKOWN", "NA");
            }
        } */ 
        
        String str = "PC; IIP: NA;LIP:UNKOWN; MAC:1831BF4BC3AB;hh:UNKOWN@;aa:UNKOWN";
        String regex = "(?<=:)[^:\\;|@]+(?=\\;|@)";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        System.out.println("replace: "+str.replaceAll(regex, "O"));  
        while (matcher.find()) {  
            if ("UNKOWN".equals(matcher.group(0))) {
                str = str.replace("UNKOWN", "NA");
            }
        }  
        System.out.println(str);
    }
    
    public static void trimNull() {
        String str = "  ";
        str = str.trim();
        System.out.println(str == null);
        System.out.println("".equals(str));
        System.out.println(str != null && !str.equals(""));
    }
    
    public static void subMsgType() {
        String srt = "";
        //srt = "{\"head\": {\"msgType\": 27009, \"sessionId\": \"SID_1000000001\", \"requestId\": 10000007}, \"request\": {\"collateralOrderInfo\": {\"acctId\": \"000199306270\", \"exchId\": \"0\", \"stkId\": \"600000\", \"orderQty\": 5000, \"orderType\": \"S\", \"orderPrice\": 11.45}}}";
        srt = "{\"head\":{\"msgType\":27009,\"requestId\":10000007,\"sessionId\":\"SID_1000000001\"},\"respond\":{\"errorCode\":\"\",\"errorMsg\":\"\",\"lastFlag\":true,\"successFlg\":0},\"data\":{\"contractNum\":\"0000020009\"}}";
        
        JSONObject json = JSON.parseObject(srt);
        String headReq = json.getString("head");
        JSONObject jsonHeadReq = JSON.parseObject(headReq);
        String msgType = jsonHeadReq.getString("msgType");
        System.out.println(msgType);
    }
    
    public static void subRequestId(){
        String srt = "take from queue=10000007,";
        String orderIdName = "take from queue=";
        int idx = srt.indexOf(orderIdName);
        String res = srt.substring(idx+orderIdName.length(), srt.indexOf(",", idx));
        System.out.println(res);
    }
    
    
    public static void sepIndex(){
        String ct = "A:B";
        String res0 = ct.substring(0, ct.indexOf(":"));
        String res = ct.substring(ct.indexOf(":")+1, ct.length());
        System.out.println(res0);
        System.out.println(res);
    }
    
    public static void startIndex(){
        String str = "a;b;c";
        String res = str.substring(0, str.indexOf(";"));
        System.out.println(res);
    }
    
    public static void subStartIndex(){
        /*String srt = "OrderId(5066)=0|";
        String orderIdName = "OrderId(5066)";
        int idx = srt.indexOf(orderIdName);
        String res = srt.substring(idx+orderIdName.length()+1, srt.indexOf("|", idx));
        System.out.println(res);*/
        /*
         * |MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4|outsideInfo=PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
         */
        String srt = "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4|outsideInfo=PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4";
        String orderIdName = "insideInfo";
        int idx = srt.indexOf(orderIdName);
        String res = srt.substring(idx+orderIdName.length()+1, srt.indexOf("|", idx));
        System.out.println(res);
    }
    
    public static void splitLastComm(){
        String str = "a,";
        str = str.substring(0, str.length()-1);
        System.out.println(str);
    }
    
    public static void splitKey(){
        String sep = "^";
        String str = "a"+sep+"b";
        String sExch = str.split("\\^")[0];
        String sStk = str.split("\\^")[1];
        String exchId = str.substring(0, 1);
        String stkId = str.substring(2);
        System.out.println(str.indexOf("^")+"\t"+sExch+"\t"+sStk);
    }
    
    
    public static void splitChar(){
        String sep = "@";
        String str = "a"+sep+"b";
        String[] arr = str.split(sep);
        for (String res : arr) {
            System.out.println(res);
        }
    }
    
    public static void testIndex(){
        String str = "\"abc";
        System.out.println(str.indexOf("a"));
    }
    
    public static void splitDemo(){/*
        
        //HashSet<String> hashSet = StringUtils.split1("CIH=IG^CTF=TF^CFT=T^SCP=sc^ISC=sc^IOE=i^CTS=TS", "^");
        HashSet<String> hashSet = StringUtils.split1("CIH=IG", "^");
        hashSet.contains("A");
        System.out.println(hashSet);
        
        String futRicRelation = "CIH=IG^CTF=TF^CFT=T^SCP=^ISC=sc^IOE=i^CTS=TS";
        
        String compId = "IF";
        if (ParaChecker.isValidPara(futRicRelation)) {
            
            String[] futRicRelationArr = StringUtils.split(futRicRelation, "^");
            for (String futRICC : futRicRelationArr) {
                String[] futArr = StringUtils.split(futRICC, "=");
                if (futArr[0].toUpperCase().equals("SCP")) {
                    if (futArr.length > 1) {
                        compId = futArr[1];
                    }
                }
            }
        }
        System.out.println(compId);
    */}
    
}
