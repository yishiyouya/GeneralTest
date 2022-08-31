package mywork.myregex;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scala.collection.generic.BitOperations.Int;


public class MyRegexTest {

    private static int eachSubNum = 2;
    
    private static String andConn = " AND ";
    
    public static void main(String[] args) {
        subStrTest();
    }

    public static void subStrTest() {
        //String pattern = "^\\s*([^\\(^\\)]*in\\s*\\([^\\(^\\)]*\\))+\\s*$";
        String pattern = "\\s*[^\\(^\\)]*in\\s*\\([^\\(^\\)]*\\)";//匹配所有in
        String content = "funCode in ('0010034') AND stkId in ('0010032', '0010033', '0010034') AND exchId in ('F', 'D', 'S')";
        //String content = "stkid in ('0010032', '0010033', '0010034') and exchid in ('F', 'D', 'S')";
        System.out.println("msgselector:"+content);
        Pattern pattern2 = Pattern.compile(pattern);
        Matcher matcher = pattern2.matcher(content);
        
        List<List<String>> subListInfo = new ArrayList<List<String>>();
        int fieldCnt = 0;
        String field = "";
        String matRes = "";
        while (matcher.find()) {
            matRes = matcher.group(0);
            matRes = matRes.replace("and", "").trim().replace("AND", "").trim();
            field = matRes.substring(0, matRes.toUpperCase().indexOf("IN")).trim();
            System.out.println("field: "+ field +"\t" + "matRes:" +matRes);
            List<String> resList = splitCon(true, field, matRes);
            subListInfo.add(resList);
            fieldCnt++;
        }
        
        //拼接分割后的订阅信息
        contactList(fieldCnt, subListInfo);
        
    }
    
    /**
     * 拼接分割后的订阅信息
     * @param subListInfo
     */
    public static void contactList(int fieldCnt, List<List<String>> subListInfo) {
        List<String> queryInfoList = new ArrayList<String>();
        int size = subListInfo.get(0).size();
        for (int i = 0; i < subListInfo.size() - 1; i++) {
            size = Math.max(subListInfo.get(i).size(), subListInfo.get(i+1).size());
        }
        for (int i = 0; i < size; i++) {
            String temp = "";
            for (int j = 0; j < fieldCnt; j++) {
                if (i >= subListInfo.get(j).size()) {
                    temp += (subListInfo.get(j).get(0) + andConn);
                }else{
                    temp += (subListInfo.get(j).get(i) + andConn);
                }
            }
            int idx = temp.lastIndexOf(andConn);
            temp = temp.substring(0, idx);
            queryInfoList.add(temp);
            System.out.println(temp);
        }
    }
    
    /*
     * funCode in ('0010034') and stkid in ('0010032', '0010033', '0010034') and exchid in ('F', 'D', 'S')
     * 每批最多2条
     * FUNCODE IN ('0010034') and STKID in ('0010032', '0010033') and EXCHID in ('F', 'D')
     * FUNCODE IN ('0010034') and STKID in ( '0010034') and EXCHID in ( 'S')
     */
    public static List<String> splitCon(boolean sepFlag, String field, String messageSelector){
        List<String> resList = null;
        if (sepFlag
            && messageSelector.indexOf("(") > -1
            && messageSelector.indexOf(")") > -1) {
            String newMessageSelector =
                messageSelector.substring(
                    messageSelector.indexOf("(") + 1,
                    messageSelector.lastIndexOf(")"));
            String[] msgArr = split(newMessageSelector, ",");
            int arrLen = msgArr.length;
            String eachMsg = "";
            resList = new ArrayList<String>();
            StringBuilder msgSbBuilder = new StringBuilder();
            if (arrLen <= eachSubNum) {
                resList.add(messageSelector);
            } else {
                //按批次取整
                int intWhol = arrLen / eachSubNum * eachSubNum;
                for (int i = 0; i < arrLen; i++) {
                    msgSbBuilder.append(",").append(msgArr[i]);
                    if ((i + 1) % eachSubNum == 0) {
                        eachMsg = msgSbBuilder.append(")").toString().substring(1);
                        eachMsg = field+" in (" + eachMsg;
                        resList.add(eachMsg);
                        msgSbBuilder.setLength(0);
                    }
                    if (i >= intWhol) {
                        eachMsg = "";
                        if (i == arrLen - 1) {
                            eachMsg = msgSbBuilder.append(")").toString().substring(1);
                            eachMsg = field+" in (" + eachMsg;
                            resList.add(eachMsg);
                        }
                    }
                }
            }
        }
        return resList;
    }

    public static String[] split(String str, String flag) {
        if (str == null)
            return null;

        StringTokenizer strTokenizer = new StringTokenizer(str, flag);

        if (strTokenizer.countTokens() > 0) {
            String[] result = new String[strTokenizer.countTokens()];

            int i = 0;
            while (strTokenizer.hasMoreTokens()) {
                result[i++] = strTokenizer.nextToken();
            }

            return result;
        } else
            return null;
    }
    
}
