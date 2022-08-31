package myutil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringUtil {
    
    private static Logger log = LoggerFactory.getLogger(StringUtil.class);

    private static String andConn = " AND ";
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int getIntValue(String str) {
        int temp = 0;
        try {
            if (!isEmpty(str)) {
                temp = Integer.parseInt(str);
            }
        } catch (Exception e) {
        }
        return temp;
    }

    public static int getLength(String str) {
        int bytesLength = str.length();
        try {
            bytesLength = str.getBytes("UTF-8").length;
        } catch (Exception e) {
            return str.length();
        }
        if (bytesLength != str.length()) {
            return str.length() + (bytesLength - str.length()) / 2;
        }
        return str.length();
    }
    
    /**
     * 值为null则设为""
     * @param str
     * @return
     */
    public static String setEmptyStr(String str) {
        if(str == null){
            return ""; 
        }else{
            return str;
        }
    }
    
    /**
     * int[]转double[]
     * @param intArray
     * @return
     */
    public static double[] toDoubleArray(int[] intArray) {
        if (intArray == null)
            return null;
        int len = intArray.length;
        double[] doubleArray = new double[len];
        for (int i = 0; i < len; i++) {
            doubleArray[i] = intArray[i];
        }

        return doubleArray;
    }
    
    /**
     * double最小值转换null
     * @param doubleArr
     * @return
     */
    public static Object[] toNull(double[] doubleArr){
        int len = doubleArr.length;
        Object[] objects = new Object[len];
        for(int i=0;i<len;i++){
            double d1 = doubleArr[i];
            if(Double.compare(d1, Double.MIN_VALUE) != 0){
                objects[i] = d1;
            }
        }
        
        return objects;
    }
    
    /**
     * double类型int最小值转换null
     * @param doubleArr
     * @return
     */
    public static Object[] toFutNull(double[] doubleArr){
        int len = doubleArr.length;
        Object[] objects = new Object[len];
        for(int i=0;i<len;i++){
            double d1 = doubleArr[i];
            if(Double.compare(d1, Integer.MIN_VALUE) != 0){
                objects[i] = d1;
            }
        }
        
        return objects;
    }
    
    /**
     * int最小值转换null
     * @param intArr
     * @return
     */
    public static Object[] toNull(int[] intArr){
        int len = intArr.length;
        Object[] objects = new Object[len];
        for(int i=0;i<len;i++){
            double d1 = intArr[i];
            if(d1 != Integer.MIN_VALUE){
                objects[i] = d1;
            }
        }
        
        return objects;
    }
    
    /**
     * int最小值转换
     * @param i
     * @return
     */
    public static Integer toNull(int i) {
        if (i == Integer.MIN_VALUE)
            return null;
        else
            return i;

    }
    
    /**
     * long最小值转换
     * @param l
     * @return
     */
    public static Long toNull(long l) {
        if (l == Long.MIN_VALUE)
            return null;
        else
            return l;

    }
    
    /**
     * double最小值转换
     * @param d
     * @return
     */
    public static Double toNull(double d) {
        if (d == Double.MIN_VALUE)
            return null;
        else
            return d;
    }
    
    /**
     * 把以"^"分隔的字符串转换成List<String>的形式
     * @param str
     * @param flag
     * @return
     */
    public static List<String> splitToList(String str, String flag) {
       if (str == null)
           return null;

       StringTokenizer strTokenizer = new StringTokenizer(str, flag);

       if (strTokenizer.countTokens() > 0) {
           List<String> result = new ArrayList<String>();

           while (strTokenizer.hasMoreTokens()) {
               result.add(strTokenizer.nextToken());
           }

           return result;
       } else
           return null;
    }
    
    /**
     * 把以"^"分隔的字符串转换成集合的形式
     */
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
    

    /**
     * 根据终端信息获取MAC
     * @param terminalInfo
     * @return
     */
    public static String getMac(String terminalInfo) {
        String oldSplitCon = ":";
        String newSplitCon = "=";
        String mac = "";
        if(terminalInfo == null)
            return null;
        
        try{
            StringTokenizer st = new StringTokenizer(terminalInfo, ";");
            while (st.hasMoreElements()) {
                String ter = st.nextToken();
                if (ter.contains("MAC")){
                    String macIdx = "MAC" + oldSplitCon;
                    if (ter.indexOf(macIdx) < 0) {
                        macIdx = "MAC" + newSplitCon;
                    }
                    mac = ter.substring(ter.indexOf(macIdx) + macIdx.length(), ter.length());
                    break;
                }
            }
        }catch(Throwable e){
            log.error("获取终端信息失败", e);
        }
        
        return mac;
    }
    
}
