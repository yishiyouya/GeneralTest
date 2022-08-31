package myutil;


public class ParaChecker {
    /**
     * 检查参数有效性（单个）
     * @param para
     * @return 若该参数有效，返回TRUE；否则返回FALSE
     */
    public static boolean isValidPara(String para) {
        if (para == null || para.equals(""))
            return false;
        else
            return true;
    }

    public static boolean isValidPara(Short para) {
        if (null == para || para == Integer.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(short para) {
        if (para == Short.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(Integer para) {
        if (null == para || para == Integer.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(int para) {
        if (para == Integer.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(Long para) {
        if (null == para || para == Long.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(long para) {
        if (para == Long.MIN_VALUE)
            return false;
        else
            return true;
    }

    public static boolean isValidPara(Double para) {
        if (null == para || para == Double.MIN_VALUE)
            return false;
        else
            return true;
    }
    
    public static boolean isValidPara(double para) {
        if (para == Double.MIN_VALUE)
            return false;
        else
            return true;
    }

    public static boolean isValidDatePara(Long para) {
        if (null == para || para == Long.MIN_VALUE || para == -1 || para ==  0)
            return false;
        else
            return true;
    }
    
    public static boolean isValidDatePara(long para) {
        if (para == Long.MIN_VALUE || para == -1 || para ==  0)
            return false;
        else
            return true;
    }

    public static boolean isNull(String para) {
        if (para == null || para.equals(""))
            return true;
        else
            return false;
    }
    
    public static boolean isNull(long para) {
        if (para == Long.MIN_VALUE)
            return true;
        else
            return false;
    }
    
    public static boolean isNull(Integer para) {
        if (para == null || para == Integer.MIN_VALUE)
            return true;
        else
            return false;
    }
    
    public static boolean isValidPara(char para) {
        if (para == '\0')
            return false;
        else
            return true;
    }

}
