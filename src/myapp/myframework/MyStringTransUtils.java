package myapp.myframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import myutil.ParaChecker;

public class MyStringTransUtils {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testTokenNew();
        //testTerRepCotentNew();
    }
    
    
    public static void testTerRepCotentNew() {
        String transedType = "PC";
        String separator = ";";
        String connector = ":";
        //String newTerminalInfo = "PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL";
        //String newTerminalInfo = "PC;MAC:1831BF4BC3AB;NLV:@";
        //String newTerminalInfo = "PC;MAC:1831BF4BC3AB;HD:@";
        String newTerminalInfo = "PC;CPU:Unkown;IPU:[112];IIP:[abc;LIP:88];MAC:(74D435BFA458;HD:@123";
        String extSym = "@";
        String terRepCotent = "Unkown:NA^[*]:NA^[*:NA^*]ab:NA^(*:NA^*):NA"; 
        
        String[] termArr = newTerminalInfo.split(separator);
        List<String> termList = new ArrayList<String>(Arrays.asList(termArr));
        Map<String, String> termMap = new LinkedHashMap<String, String>();
        for (String term : termList) {
            if (term.lastIndexOf(connector) > -1) {
                termMap.put(term.substring(0, term.lastIndexOf(connector)), term.substring(term.lastIndexOf(connector)+connector.length(), term.length()));
            }
        }
        System.out.println(termMap);
        
        if (ParaChecker.isValidPara(terRepCotent)) {
            StringTokenizer st = new StringTokenizer(terRepCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                String before = ct.substring(0, ct.indexOf(":"));
                String after = ct.substring(ct.indexOf(":")+1, ct.length());
                for (Entry<String, String> termEntry : termMap.entrySet()) {
                    String termVal = termEntry.getValue();
                    String start = "";
                    String end = "";
                    boolean matched = true;
                    if (before.indexOf("*") > -1) {
                        start = before.substring(0, before.indexOf("*"));
                        end = before.substring(before.indexOf("*") + 1, before.length());
                        if (ParaChecker.isValidPara(start)) {
                            matched = matched && termVal.startsWith(start);
                        }
                        if (ParaChecker.isValidPara(end)) {
                            matched = matched && termVal.endsWith(end);
                        }
                        
                        if (matched) {
                            termMap.put(termEntry.getKey(), after);
                        }
                    } else {
                        if (ParaChecker.isValidPara(termVal) && termVal.equals(before)) {
                            termMap.put(termEntry.getKey(), after);
                        }
                    }
                    
                }
            }
        }
        String newTerminalMap = "";
        for (Entry<String, String> termEntry : termMap.entrySet()) {
            newTerminalMap += termEntry.getKey() + connector + termEntry.getValue() + separator;
        }
        newTerminalMap = transedType + separator + newTerminalMap.substring(0, newTerminalMap.length() - 1);
        System.out.println("termMap: " + termMap);
        System.out.println("newTerminalMap: " + newTerminalMap);
    }
    
    public static void testTokenNew() {
        String transedType = "PC";
        String separator = ";";
        String connector = ":";
        String newSeparator = "";
        String newConnector = "=";
        String terTypeCotent = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^NLV^CPU";
        //String newTerminalInfo = "PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL";
        //String newTerminalInfo = "PC;MAC:1831BF4BC3AB;NLV:@";
        //String newTerminalInfo = "PC;MAC:1831BF4BC3AB;HD:@";
        //String newTerminalInfo = "PC;CPU:234;IIP:192.168.3.254;LIP:192.168.3.27;MAC:74D435BFA458;HD:@123";
        String newTerminalInfo = "PC;CPU=abc;FOX:333;VOL:*;PI:[2323];MAC:1831BF4BC3AB;HD:@23";
        String extSym = "@";
        String extendInfo = "";
        if (ParaChecker.isValidPara(newConnector) && ParaChecker.isValidPara(newTerminalInfo)) {
            if (newTerminalInfo.indexOf(newConnector) > -1) {
                transConnector(newTerminalInfo, terTypeCotent, newSeparator, newConnector, connector);
            }
        }
        if (ParaChecker.isValidPara(newSeparator)) {
            if (newTerminalInfo.indexOf(newSeparator) > -1) {
                transSeparator(newTerminalInfo, terTypeCotent, newSeparator, separator);
            }
        }
        if (newTerminalInfo.indexOf(extSym) > -1) {
            extendInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(extSym) + extSym.length(), newTerminalInfo.length());
            System.out.println("extendInfo: " + extendInfo);
            newTerminalInfo = newTerminalInfo.substring(0, newTerminalInfo.indexOf(extSym) + extSym.length());
            
        }
        String mustSupCotent = "NA";
        newTerminalInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(separator)+separator.length(), newTerminalInfo.length());
        
        String[] termArr = newTerminalInfo.split(separator);
        List<String> termList = new ArrayList<String>(Arrays.asList(termArr));
        List<String> terTypeCotentList = new ArrayList<String>();
        List<String> termFinalList = new ArrayList<String>();
        Map<String, String> termMap = new LinkedHashMap<String, String>();
        for (String term : termList) {
            if (term.lastIndexOf(connector) > -1) {
                termMap.put(term.substring(0, term.lastIndexOf(connector)), term.substring(term.lastIndexOf(connector)+connector.length(), term.length()));
            }
        }
        System.out.println("termMap: == " + termMap);
        //PC:�ж�ͨ����ȥ��
        //PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL
        //PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD@TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
        terTypeCotent = terTypeCotent.substring(terTypeCotent.indexOf(":")+1, terTypeCotent.length());
        StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
        while (st.hasMoreTokens()) {
            String ct = st.nextToken();
            if (ct.indexOf("*") > -1) {
                ct = ct.replace("*", "");
                
                if (null == termMap.get(ct)) {
                    termMap.put(ct, mustSupCotent);
                } else {
                    String termVal = termMap.get(ct);
                    System.out.println("termVal: " + termVal);
                    if (ParaChecker.isValidPara(termVal)) {
                        if ((termVal.startsWith(extSym) || ParaChecker.isNull(termVal))) {
                            String apd = mustSupCotent;
                            /*if (termVal.startsWith(extSym)) {
                                apd += extSym;
                            }*/
                            termMap.put(ct, apd);
                        }
                    }
                }
            }
            terTypeCotentList.add(ct);
        }
        System.out.println("termMap: " + termMap);
        /*for (int i = 0; i < terTypeCotentList.size(); i++) {
            if (ParaChecker.isValidPara(termMap.get(terTypeCotentList.get(i)))) {
                termFinalList.add(terTypeCotentList.get(i)+connector+termMap.get(terTypeCotentList.get(i)));
            }
        }
        for (Entry<String, String> entryTerm : termMap.entrySet()) {
            if (termFinalList.indexOf(entryTerm.getKey()+connector+entryTerm.getValue()) < 0) {
                termFinalList.add(entryTerm.getKey()+connector+entryTerm.getValue());
            }
        }*/
        for (int i = 0; i < terTypeCotentList.size(); i++) {
            if (ParaChecker.isValidPara(termMap.get(terTypeCotentList.get(i)))) {
                termFinalList.add(terTypeCotentList.get(i)+connector+termMap.get(terTypeCotentList.get(i)));
            }
            termMap.remove(terTypeCotentList.get(i));
        }
        newTerminalInfo = String.join(separator, termFinalList);
        termFinalList.clear();
        for (Entry<String, String> entryTerm : termMap.entrySet()) {
            termFinalList.add(entryTerm.getKey()+connector+entryTerm.getValue());
        }
        newTerminalInfo = String.join(newSeparator, termFinalList);
        System.out.println("termFinalList: " + termFinalList);
        /*for (String appenTerm : termList) {
            System.out.println("appenTerm:"+appenTerm);
            for (String finalTerm : termFinalList) {
                System.out.println("finalTerm:"+finalTerm);
                
            }
        }*/
        newTerminalInfo = transedType + separator + newTerminalInfo + extSym + extendInfo;
        System.out.println(newTerminalInfo);
    }
    
    /**
     * 5. ת�����ӷ�
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param connector
     * @param newConnector
     */
    public static String transConnector(String newTerminalInfo, String terTypeCotent, String separator, String connector, String newConnector) {
        /*
         * �� ����������ӷ�����Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP:'��
         * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
         * ������ڣ������еġ����ӷ����滻Ϊ�������ӷ�����
         */
        if (ParaChecker.isValidPara(newConnector)) {
            if (newConnector.equals(connector)) {
                return newTerminalInfo;
            }
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf("*") > -1) {
                    ct = ct.replace("*", "");
                }
                String oldCt = separator + ct + connector;
                String newCt = separator + ct + newConnector;
                if (newTerminalInfo.indexOf(oldCt) > -1) {
                    newTerminalInfo = newTerminalInfo.replace(oldCt, newCt);
                }
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * 6. ת���ָ���
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param newSeparator
     */
    public static String transSeparator(String newTerminalInfo, String terTypeCotent, String separator, String newSeparator) {
        /*
         * �� ������·ָ�������Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ����������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP'��
         * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
         * ������ڣ������еġ��ָ������滻Ϊ���·ָ�������
         */
        if (ParaChecker.isValidPara(newSeparator)) {
            if (newSeparator.equals(separator)) {
                return newTerminalInfo;
            }
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf("*") > -1) {
                    ct = ct.replace("*", "");
                }
                String oldCt = separator + ct;
                String newCt = newSeparator + ct;
                if (newTerminalInfo.indexOf(oldCt) > -1) {
                    newTerminalInfo = newTerminalInfo.replace(oldCt, newCt);
                }
            }
        }
        return newTerminalInfo;
    }
    
    public static void testToken() {
        String transedType = "PC";
        String separator = ";";
        String connector = ":";
        String terTypeCotent = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^NLV";
        //String newTerminalInfo = "PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL";
        //String newTerminalInfo = "PC;MAC:1831BF4BC3AB;NLV:@";
        String newTerminalInfo = "PC;MAC:1831BF4BC3AB;HD:@";
        
        String extSym = "@";
        String mustSupCotent = "NA";
        newTerminalInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(separator)+separator.length(), newTerminalInfo.length());
        
        String[] termArr = newTerminalInfo.split(separator);
        List<String> termList = new ArrayList<String>(Arrays.asList(termArr));
        List<String> termTmpList = new ArrayList<String>();
        List<String> terTypeCotentList = new ArrayList<String>();
        List<String> termFinalList = new ArrayList<String>();
        for (String term : termList) {
            if (term.lastIndexOf(connector) > -1) {
                termTmpList.add(term.substring(0, term.lastIndexOf(connector)));
            }
        }
        
        //PC:�ж�ͨ����ȥ��
        //PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL
        //PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD@TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
        terTypeCotent = terTypeCotent.substring(terTypeCotent.indexOf(":")+1, terTypeCotent.length());
        StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
        while (st.hasMoreTokens()) {
            String ct = st.nextToken();
            if (ct.indexOf("*") > -1) {
                ct = ct.replace("*", "");
                terTypeCotentList.add(ct);
                
                
                int idxC = termTmpList.indexOf(ct);
                if (idxC > -1) {
                    if ((termList.get(idxC).endsWith(connector)
                            || termList.get(idxC).endsWith(connector+extSym)
                            || termList.get(idxC).endsWith(ct))) {
                        String apd = termTmpList.get(idxC) + connector + mustSupCotent;
                        if (termList.get(idxC).contains(connector+extSym)) {
                            apd += extSym;
                        }
                        termList.add(idxC, apd);
                        termList.remove(idxC+1);
                    }
                } else {
                    if (idxC < 0) {
                        idxC = 0;
                    } else if (idxC == 0) {
                        idxC += 1;
                    }
                    termList.add(idxC + 1, ct + connector + mustSupCotent);
                    termTmpList.add(idxC + 1, ct);
                }
            }
        }
        for (int i = 0; i < termList.size(); i++) {
            if (i < terTypeCotentList.size()) {
                for (int j = 0; j < termList.size(); j++) {
                    if (termList.get(j).startsWith(terTypeCotentList.get(i))) {
                        termFinalList.add(termList.get(j));
                    }
                }
            } else {
                termFinalList.add(termList.get(i));
            }
        }
        newTerminalInfo = transedType + separator + String.join(separator, termFinalList);
        
        System.out.println(terTypeCotentList);
        System.out.println(termFinalList);
        System.out.println(termList);
        newTerminalInfo = transedType + separator + String.join(separator, termFinalList);
        
        System.out.println(newTerminalInfo);
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
