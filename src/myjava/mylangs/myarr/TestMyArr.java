package myjava.mylangs.myarr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myutil.ParaChecker;

public class TestMyArr {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        getNaviArr();
    }

    public static void getNaviArr() {
        int[] arr = getNaviArrV();
    
    }
    
    public static int[] getNaviArrV() {
        return new int[-1];
    }
    
    public static void forEachToArr(){
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < 2; i++) {
            String[] a = new String[2];
            a[0] = i+"a";
            a[1] = i+"b";
            list.add(a);
        }
        System.out.println(Arrays.asList(list.get(0)[0] + "\n" + list.get(1)[0]));
        
    }
    
    public static int[] getStartEndTime() {
        int tradeStartTime = 800;
        int tradeEndTime = 800;
        int startTime = 800;
        int endTime = 1800;
        int[] strEndArr = new int[]{tradeStartTime, tradeEndTime};
        try {
            String alGwTime = "1000^2000";
            if (ParaChecker.isValidPara(alGwTime) && alGwTime.indexOf("^") > -1) {
                startTime = Integer.valueOf(alGwTime.split("\\^")[0]);
                endTime = Integer.valueOf(alGwTime.split("\\^")[1]);
                startTime = 0 == startTime ? tradeStartTime : startTime;
                endTime = 0 == endTime ? tradeEndTime : endTime;
                if (startTime >= endTime) {
                    startTime = tradeStartTime;
                    endTime = tradeEndTime;
                }
            }
        } catch (Exception e) {
            startTime = tradeStartTime;
            endTime = tradeEndTime;
        }
        strEndArr[0] = startTime;
        strEndArr[1] = endTime;
        System.out.println(strEndArr[0]+" "+strEndArr[1]);
        return strEndArr;
    }
    
}
