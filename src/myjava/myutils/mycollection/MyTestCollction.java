package myjava.myutils.mycollection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;



public class MyTestCollction {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        isCollNull();
    }
    
    public static void isCollNull(){
        Set<String> tSet = new HashSet<String>();
        tSet.add("f");
        boolean ep = CollectionUtils.isEmpty(tSet);
        System.out.println(ep);
    }

}
