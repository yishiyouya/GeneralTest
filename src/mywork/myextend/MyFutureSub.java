package mywork.myextend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class MyFutureSub {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        futureSubOr();
    }

    public static void futureSubIn(){
        List<String> sub = new ArrayList<String>();
        
        sub.add("FIF2006");
        sub.add("DSP j1911_j1912");
        
        StringBuffer filter = new StringBuffer();
        if (null != sub && sub.size() > 0) {
            Set<String> exchSet = new HashSet<String>();
            Set<String> stkSet = new HashSet<String>();
            for(int i=0;i<sub.size();i++){
                String subInfo = sub.get(i).toString();
                exchSet.add(subInfo.substring(1));
                stkSet.add(subInfo.substring(1, subInfo.length()));
                filter.append("exchId in (" + exchSet.toString() + ")" + " AND " + "stkId in (" + stkSet.toString() + ")");
            }
        }
        
    }
    
    public static void futureSubOr(){
        List<String> sub = new ArrayList<String>();
        
        sub.add("FIF2006");
        sub.add("DSP j1911_j1912");
        
        StringBuffer filter = new StringBuffer();
        
        if (null != sub && sub.size() > 0) {
            StringBuffer filterTemp = new StringBuffer();
            String splitStr = " OR ";
            for(int i=0;i<sub.size();i++){
                
                String subInfo = sub.get(i).toString();
                filterTemp.append("exchId = '" + subInfo.substring(0, 1) + "'" + " AND " + "stkId  = '" + subInfo.substring(1, subInfo.length()) + "'");
                filterTemp.append(splitStr);
            }
            String filterTempStr = filterTemp.toString();
            filter.append(filterTempStr.substring(0, filterTempStr.lastIndexOf(splitStr)));
            System.out.println(filter.toString());
        }
        
    }
    
}
