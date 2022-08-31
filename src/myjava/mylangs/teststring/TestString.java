package myjava.mylangs.teststring;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Query;

import org.apache.commons.beanutils.converters.SqlDateConverter;

public class TestString {/*

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testTokenizer1();
    }

    public static void subPar(){
        String strDate = "2019-02-02";
        if (ParaChecker.isValidPara(strDate)) {
            int year = Integer.valueOf(strDate.substring(0, 4));
            int month = Integer.valueOf(strDate.substring(5, 7));
            System.out.println(year+":"+month);
        }
    }
    
    public static void sepMessageList(){
        List<String> resList = null;
        String messageSelector = ""
            + "stkId in "
            + "('0600132','0600131','1000582','0600533','1000825',"
            + "'0600329','0600802')";
        int eachSubNum = 5;
        if (messageSelector.indexOf("(") > -1
            && messageSelector.indexOf(")") > -1) {
            String newMessageSelector =
                messageSelector.substring(
                    messageSelector.indexOf("(") + 1,
                    messageSelector.lastIndexOf(")"));
            String[] msgArr = newMessageSelector.split(",");
            int arrLen = msgArr.length;
            String eachMsg = "";
            resList = new ArrayList<String>();
            StringBuilder msgSbBuilder = new StringBuilder();
            if (arrLen <= eachSubNum) {
                resList.add(messageSelector);
            } else {
                for (int i = 0; i < arrLen; i++) {
                    msgSbBuilder.append(",").append(msgArr[i]);
                    if ((i + 1) % eachSubNum == 0) {
                        eachMsg = msgSbBuilder.append(")").toString().substring(1);
                        eachMsg = "stkId in (" + eachMsg;
                        resList.add(eachMsg);
                        msgSbBuilder.setLength(0);
                    }
                    if (i >= arrLen / eachSubNum * eachSubNum) {
                        eachMsg = "";
                        if (i == arrLen - 1) {
                            eachMsg = msgSbBuilder.append(")").toString().substring(1);
                            eachMsg = "stkId in (" + eachMsg;
                            resList.add(eachMsg);
                        }
                    }
                }
            }
            for (String resStr : resList) {
                System.out.println(resStr);
            }
        }
    }
    
    public static void sepMessage(){
        
         * stkId in ('0600132','0600131','0603008','1000582','0600533','0600333','0600651','1000825','0738076','0600093','0600537');
         * stkId in ('0600132','0600131','0603008','1000582','0600533');
         * stkId in ('0600333','0600651','1000825','0738076','0600093');
         * stkId in ('0600537');
         
        String message= "stkId in ('0600132','0600131','0603008','1000582','0600533','0600333','0600651','1000825','0738076','0600093','0600537')";
        String regex = "(?<=\\().+(?=\\))";
        String matched = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            matched = matcher.group();
        }
        matched = message.substring(message.indexOf("(")+1, message.lastIndexOf(")"));
        
        String[] arrMsg = matched.split(",");
        
        int arrLen = arrMsg.length;
        int eachNum = 20;
        List<String> resList = new ArrayList<String>(); 
        String eachMsg = "";
        StringBuilder msgSbBuilder = new StringBuilder();
        if (arrLen <= eachNum) {
            resList.add(message);
        }
        for (int i = 0; i < arrLen; i++) {
            msgSbBuilder.append(",").append(arrMsg[i]);
            if ((i + 1) % eachNum == 0 || (arrLen > eachNum && i >= arrLen / eachNum * eachNum)) {
                eachMsg = msgSbBuilder.append(")").toString().substring(1);
                eachMsg = "stkId in (" + eachMsg;
                System.out.println(eachMsg);
                resList.add(eachMsg);
                msgSbBuilder.setLength(0);
            }
        }
        System.out.println(resList);
    }
    
    public static void moniotrCS() {
        //行情代码——>"0:152087,600000;1:000001"(市场代码：证券代码)
        String request = "0:152087,600000;1:000001;";
        String[] singleExch = StringUtils.split(request, ";");
        if (null != singleExch) {
            for (String sExch : singleExch) {
                String[] exchStks = StringUtils.split(sExch, ":");
                if (null != exchStks) {
                    String exchId = exchStks[0];
                    if (exchStks.length > 1) {
                        String[] stkIds = StringUtils.split(exchStks[1], ",");
                        if (null != stkIds) {
                            for (String stkId : stkIds) {
                                System.out.println(exchId+"*"+stkId + ", " + stkId);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void regexTest() {
        String str = "quotaion(行情服务)";//测试字符串
        String skh = "\\w+(?=\\()";//用于匹配(前面的文本
        Pattern pattern = Pattern.compile(skh);
        Matcher matcher = pattern.matcher(str);
        boolean is = matcher.find();
        if (is)
            System.out.println(matcher.group());
        
        String skh1 = "(?<=\\().+(?=\\))";//用于匹配()里面的文本
        pattern = Pattern.compile(skh1);
        matcher = pattern.matcher(str);
        boolean is1 = matcher.find();
        if (is1)
            System.out.println(matcher.group());
    }
    
    public static void maSlavTest(){
        String replicateSlave = "# Replication\n"
                +"role:slave\n"
                +"redisHost:192.168.1.161\n"
                +"redisPort:1628\n"
                +"master_host:192.168.1.13\n"
                +"master_port:1628\n"
                +"repl_backlog_active:0\n"
                +"repl_backlog_size:1048576\n"
                +"repl_backlog_first_byte_offset:0\n"
                +"repl_backlog_histlen:0\n";
        
        String replicateMaster = "# Replication\n"
                +"role:master\n"
                +"connected_slaves:2\n"
                +"redisHost:192.168.1.13\n"
                +"redisPort:1628\n"
                //+"slave0:ip=192.168.1.161,port=1628,state=online,offset=136714425062,lag=0\n"
                //+"slave1:ip=192.168.1.162,port=1628,state=online,offset=136714425062,lag=0\n"
                +"repl_backlog_active:0\n"
                +"repl_backlog_size:1048576\n"
                +"repl_backlog_first_byte_offset:0\n"
                +"repl_backlog_histlen:0\n";
        
        String replicate = "";
        //0 slave
        int i = 1;
        if (i == 0) {
            replicate = replicateSlave;
        } else {
            replicate = replicateMaster;
        }
        
        String hostName = "192.168.1.13:1628";
        Map<String, String> repMap = new HashMap<String, String>();

        String[] reArr = StringUtils.split(replicate, "\n");
        for (String rep : reArr) {
            if (rep.indexOf(":") > -1) {
                repMap.put(rep.substring(0, rep.indexOf(":")), rep
                    .substring(rep.indexOf(":") + 1)
                    .trim());
            }
        }
        
        String clusInfo = "";
        
         * 根据Redis返回信息，设置集群IP主、从
         
        //当前Redis为slave, 存在master_host
        if (repMap.containsKey("master_host")) {
            if ((repMap.get("master_host") + ":" + repMap.get("master_port")).equals(hostName)) {
                clusInfo += "\tMaster:";
            } else if ((repMap.get("redisHost") + ":" + repMap.get("redisPort")).equals(hostName)) {
                clusInfo += "\tSlaver:";
            }
        } else {
            if ((repMap.get("redisHost") + ":" + repMap.get("redisPort")).equals(hostName)) {
                clusInfo += "\tMaster:";
            } else {
                int slaveNum = Integer.valueOf(repMap.get("connected_slaves"));
                for (int j = 0; j < slaveNum; j++) {
                    //ip=192.168.1.161,port=1628,state=online,offset=136714425062,lag=0
                    String slaveInfo = repMap.get("slave"+j);
                    slaveInfo = slaveInfo.replace("ip=", "").replace(",port=", ":");
                    slaveInfo = slaveInfo.substring(0, slaveInfo.indexOf(","));
                    if (slaveInfo.indexOf(hostName) > -1) {
                        clusInfo += "\tSlaver:";
                        break;
                    }
                }
            }
        }
        clusInfo += hostName;
        repMap.clear();
        System.out.println(clusInfo);
    
    }
    
    public static void mdsSplit(){
        String str = ",";
        String[] arr = com.rootnet.urm.mds.client.util.StringUtils.split(str, ",");
        if (null != arr) {
            for (String s : arr) {
                System.out.println(s);
            }
        }
    }
    
    public static void testEnco(){
        String strOri = "哈哈";
        try {
            String str = new String(strOri.getBytes(), "ISO-8859-1");
            String iso = new String(strOri.getBytes("ISO-8859-1"), "ISO-8859-1");
            String gbk = new String(strOri.getBytes("GBK"), "GBK");
            String utf = new String(strOri.getBytes("utf-8"), "utf-8");
            
            System.out.println("iso-8859-1:"+iso);
            System.out.println("GBK:"+gbk);
            System.out.println("UTF-8:"+utf);
            Properties initProp = new Properties(System.getProperties());
            System.out.println("当前系统编码:" + initProp.getProperty("file.encoding"));
            System.out.println("当前系统语言:" + initProp.getProperty("user.language"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    }
    
    
    public static void subBeStr(){
        String inforLibType = "A_SLAVES";
        
        String res = inforLibType.substring(0, inforLibType.indexOf("_SLAVES"));
        System.out.println(res);
        
    }
    
    public static void sqlMutl(){
        String table = "dc_for";
        String filed = "formulaType";
        String  var = "^DATA_DBJ";
        
        String  res = SqlUtil.spliteParam(table, filed, var);
        System.out.println(res);
    }
    
    public static void stringUtilComm() {
        String formulaType = "^^";
        String[] resArr = BLUtils.splitStr(formulaType, "^");
        
        String res = Arrays.asList(resArr).toString();
        
        System.out.println(res);
    }
    
    public static void queryUtilComm() {
        String formulaType = "^^";
        String res = QueryUtils.parseMultiCondition("formulaType", formulaType);
        System.out.println(res.equals(""));
    }
    
    public static void fType() {
        String formulaType = "^ACC_CASH^DATA_DBJ";
        String[] formulaTypeArrs = StringUtils.split2(formulaType, "^");
        
        String res = "(";
        for (String formulaT : formulaTypeArrs) {
            res += "'"+formulaT+"', ";
        }
        res = res.substring(0, res.length() - 2);
        res += ")";
        System.out.println(res);
        
        StringBuilder fType = new StringBuilder();
        fType.append("(");
        for (String formulaT : formulaTypeArrs) {
            fType.append(", '"+formulaT+"'");
        }
        fType.append(")");
        formulaType = fType.toString().substring(2, fType.toString().length() - 2);
        System.out.println(formulaType);
    }
    
    public static void inThosan(){
        String longDate = "yyyymmdd000000";
        String res = longDate.substring(0, 4);
        System.out.println(res);
    }
    
    //equal null
    public static void equalNull(){
        String strZ = "0";
        String strL = "L";
        
        boolean eq = strL.equals(strL);
        boolean eqN = strL.equals(null);
        boolean eqZ = strL.equals(strZ);
        System.out.println(eq);
        System.out.println(eqN);
        System.out.println(eqZ);
    }
    
    //compare null
    public static void comNull(){
        boolean autoExec = 1 == 0;
        String string = "Y";
        boolean eq = string.equals(null);
        System.out.println(autoExec);
    }
    
    //split ^
    public static void splitThree(){
        String ricCode = "CFT";
        String compId = "";
        
        String futureRICCodeMappingRelation = "CIC=IC^CIH=IH^CTF=TF^CFT=T^SCP=sc^ISC=sc^IOE=i^CTS=TS";
        if (futureRICCodeMappingRelation.indexOf("^") > -1) {
            String[] futureRICCodeMappingRelationArr = futureRICCodeMappingRelation.split("\\^");
            for (String futRICC : futureRICCodeMappingRelationArr) {
                if (futRICC.indexOf(ricCode) > -1 && futRICC.indexOf("=") > -1) {
                    compId =  futRICC.split("=")[1];
                }
            }
        }
        System.out.println(compId);
    }
    
    
    //forEach str
    public static void forEachStr(){
        String[] arrStrings = {"a", "b"};
        String res = "";
        for (String string : arrStrings) {
            string = string + "c";
            res += string;
        }
        System.err.println(res);
    }
    
    public static void strConIndex(){
        String str = "abcde";
        String res = "a";
        str.contains(res);
        int ind = str.indexOf(res);
        System.out.println(ind);
    }
    
    //编码
    public static void byteCharset(){
    	String sb = "a";
    	try {
			String iso8859 = new String(sb.toString().getBytes("iso8859-1"));
			String gbk = new String(sb.toString().getBytes("gbk"));
			String utf8 = new String(sb.toString().getBytes("utf-8"));
			if(iso8859.equals(sb.toString())){
			    System.out.println("iso8859");
			}else  if(gbk.equals(sb.toString())){
			    System.out.println("gbk");
			}else  if(utf8.equals(sb.toString())){
			    System.out.println("utf8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void printSql(){
    	String orderType = "B";
    	String reString = " AND orderType in ( '" + orderType + "', '0" + orderType + "'"+")";
    	System.out.println(reString);
    }
    
    public static void charToInt(){
    	char a = 52;
    	int b = Integer.parseInt(a+"", 10);
    	String priceStrategy = "c123";
    	int index = 0;
    	String re = "";
    	for(Character c : priceStrategy.substring(1).toCharArray()){
			if (c < 48 || c > 57) {
				index = 0;
				break;
			}else{
				re += (c+"");
				index = Integer.parseInt(re, 10);
			}
		}
    	System.out.println(index);
    }
    
    public static void subOneNum(){
    	String pwd = "X8G3YWRa";
    	String res = pwd.substring(1);
    	
    	System.out.println(res);
    }
    
    public static void changMeaning(){
    	String a = "\'"+"hh"+"\'";
    	String b = "and ((tr.ordertype = '0B' and fo.ordertype = 'B')" 
		        +" or    (tr.ordertype = '0S' and fo.ordertype = 'S')"
		        +" or (tr.ordertype = fo.ordertype))";
    	System.out.println(b);
    }
    
    public static void getOpt(){
    	String pwd = "X8G3YWRa";
    	System.out.println(pwd);
    }
    
    public static void encry4(){
    	try {
			String pwd = "hdfENETn";
			String pwd1 = "";// RC4Crypt.decryptInside("X8G3YWRa", "");
			String pwd2 = RC4Crypt.encrypt("666666");//.decrypt(pwd);
			System.out.println(pwd1);
			System.out.println(pwd2);
		} catch (BLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void subStrSpe(){
    	String string = "73=dfdfd1";
    	String[] sub = string.split("73=");
    	String res = "";
    	res = sub[1].substring(0, sub[1].indexOf(""));
    	System.out.println(res);
    }
    		
    public static void tprintStr(){
        //System.out.println("id\tidCard\tname");
        String fixDir = "F://File//编程实践//解析FIX tag值//精城FIX日志";
        String fileName = fixDir.substring(fixDir.lastIndexOf("//")+2, fixDir.length());
        System.out.println(fileName);
    }
    
    
    public static void testStringTokenAft(){
    	String fixLine = "1106.08:29:06.559|BLP|★|0|8=FIX.4.29=027635=E49=BLP56=CMSE34=24152=20181106-00:29:11.22250=2284236657=CMSE394=366=1EASGW7AKX4B468=273=111=3VQQ000001000167=1109=10411=Samsung REIS21=3100=SZ55=00273948=BV86QQ422=2207=SZ54=260=20181106-00:29:11.19838=855040=115=CNY59=158=LMT DOWN75=2018110610=011|0ms";
    	String tagPre = "";
    	String separa = "=";
    	String tagAft = "";
    	StringTokenizer st = new StringTokenizer(fixLine, "");
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			tagPre = st.nextToken();
			// 获取tag
			if (tagPre.contains(separa)) {
				tagAft = tagPre.substring(tagPre.lastIndexOf(separa) + 1,
						tagPre.length());
				tagAft = tagPre.substring(0, tagPre.lastIndexOf(separa) + 1);
				System.out.println(tagAft+"======"+tagPre);
			}
		}
    }
    
    
    public static void testSeparator(){
    	String hasString = "022635,";
    	String separa = "";
    	if (hasString.contains(separa)) {
    		String res = hasString.substring(hasString.lastIndexOf(separa)+1, hasString.length());
			System.out.println(res);
		}
    }
    
	public static void testTokenizer(){
    	String srcStr = "hh,aa,bb,", seperator = ",";
    	StringTokenizer strToken = new StringTokenizer(srcStr, seperator);
        int count = strToken.countTokens();
        String[] strArray = new String[count];
        int i = 0;
        while (strToken.hasMoreElements()) {
            strArray[i] = strToken.nextToken();
            System.out.println(strArray[i]);
            i++;
        }
        
    }
	
	public static void testTokenizer1(){
	    String srcStr="000000207936^000000019705^000000007721^000000030301^000000000741^000000092801";
	    String seperator = "^";
	    StringTokenizer strToken = new StringTokenizer(srcStr, seperator);
	    int count = strToken.countTokens();
	    String[] strArray = new String[count];
	    int i = 0;
	    while (strToken.hasMoreElements()) {
	        strArray[i] = strToken.nextToken();
	        System.out.println(strArray[i]);
	        i++;
	    }
	    
	}
    
    
*/}
