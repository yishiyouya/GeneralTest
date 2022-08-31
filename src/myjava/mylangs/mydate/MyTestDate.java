package myjava.mylangs.mydate;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class MyTestDate {

    public static void main(String[] args) throws ParseException {
        // TODO Auto-generated method stub
        //getLongDateTime(1594762857000l);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(System.currentTimeMillis());
    }

    
    /**
     * 把毫秒数转换成数字日期和时间。
     * @param millis 距离1970年1月1日午夜0点的毫秒数。
     * @return 返回格式为 yyyymmddhhmmss 的字符日期和时间。
     */
    public static long getLongDateTime(long millis) {
        if (millis <= 0)
            return -1;
        //    if (millis == 0) return 19700101000000l;
        //   Calendar rightNow = Calendar.getInstance();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new java.util.Date(millis));
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        String strDateTime =
            year
                + (month < 10 ? "0" + month : month + "")
                + (day < 10 ? "0" + day : day + "")
                + (hour < 10 ? "0" + hour : hour + "")
                + (minute < 10 ? "0" + minute : minute + "")
                + (second < 10 ? "0" + second : second + "");
        System.out.println(Long.parseLong(strDateTime));
        return Long.parseLong(strDateTime);
    }
    
    public static void getMilliTime(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
    }
    
    public static void miniTime() throws ParseException{
        String time = "20190823103020000";
        String format = "yyyyMMddHHmmssSSS";
        Date date = getFormatDate(time, format);
        long cur = System.currentTimeMillis();
        long div = cur - date.getTime();
        System.out.println(div);
    }
    
    public static long getTimeDiff(String time1, String time2) {
        long res = 0;
        try {
            String format = "HHmmssSSS";
            DateFormat sdf = new SimpleDateFormat(format);
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            long diff = date1.getTime() - date2.getTime();
            res = Math.abs(diff);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public static String getPointMilli(String milliTime){
        String format = "HHmmssSSS";
        String formatPoint = "HH:mm:ss.SSS";
        DateFormat sdf = new SimpleDateFormat(format);
        DateFormat sdfPoint = new SimpleDateFormat(formatPoint);
        String res = "";
        try {
            Date date = sdf.parse(milliTime);
            res = sdfPoint.format(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    
    public static long getFormatMilli(long milliTime){
        String format = "HHmmssSSS";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String res = sdf.format(milliTime);
        return Long.valueOf(res);
    }
    
    public static String getTime() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String time = format.format(new Date());

        return time;
    }
    
    public static Date getFormatDate(String time, String format) throws ParseException {
        SimpleDateFormat tempDate = new SimpleDateFormat(format);
        Date date = tempDate.parse(time);
        return date;
    }
    
    public static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    public static void getNormalTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time1 = "1566202231993";
        Date date = new Date(Long.parseLong(time1));
        long time = 267785073354528l%1000;
        long temp = Long.parseLong(tempDate.format(date).toString());//.substring(8));
        long apiTime = temp * 1000 + time;
        System.out.println(time + " " + temp + " ");
        Long res = diffTime(152819100005l, apiTime);
        System.out.println(res);
    }
    
    public static void NanoSec(){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        long startNano = System.nanoTime();
        long startMill = System.currentTimeMillis();
        long nano = System.nanoTime();
        System.out.print(sdf.format(new java.util.Date(nano)) + "<=>");
        System.out.print(sdf.format(new java.util.Date(nano / (1000))) + "<-->");
        System.out.println("nano:" + (System.nanoTime() - startNano));;
        System.out.println("mill:" + (System.currentTimeMillis() - startMill));;

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
    }

    
    public static long diffTime(long mdsTime1, long mdsTime2) {

        mdsTime1 = ((mdsTime1 / 10000000000L) % 100 * 60 * 60 + (mdsTime1 / 100000000L) % 100 * 60) * 1000000L
                + mdsTime1 % 100000000L;

        mdsTime2 = ((mdsTime2 / 10000000000L) % 100 * 60 * 60 + (mdsTime2 / 100000000L) % 100 * 60) * 1000000L
                + mdsTime2 % 100000000L;
        return mdsTime2 - mdsTime1;
    }
    
    public static String getLocalCurrentTimeMillSecond() {
        return format1.format(new Date());
    }
    
    public static void timeInt() {
        int start = 800;
        int end = 1800;
        String sEndString = "0900^2000";
        if (null != sEndString && !"".equals(sEndString) && sEndString.indexOf("^") > -1) {
            int tempStart;
            int tempEnd;
            try {
                tempStart = strToInt(sEndString.split("\\^")[0]);
                tempEnd = strToInt(sEndString.split("\\^")[1]);
                start = 0 == tempStart ? start : tempStart;
                end = 0 == tempEnd ? end : tempEnd;
            } catch (Exception e) {
                start = 800;
                end = 1800;
            }
            if (start == 0 || end == 0) {
                start = 800;
                end = 1800;
            }
        }
        System.out.println(start + "-" + end);
    }

    public static boolean legalTime(String str) {
        boolean res = true;
        SimpleDateFormat sft = new SimpleDateFormat("HHmm");
        try {
            sft.setLenient(false);
            sft.parse(str);
        } catch (ParseException e) {
            res = false;
        }
        System.out.println(res);
        return res;
    }
    
    public static int strToInt(String str){
        int res = 0;
        SimpleDateFormat sft = new SimpleDateFormat("HHmm");
        try {
            sft.setLenient(false);
            sft.parse(str);
            NumberFormat nfFormat = NumberFormat.getInstance();
            Number number = nfFormat.parse(str);
            res = number.intValue();
            System.out.println(res);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

}
