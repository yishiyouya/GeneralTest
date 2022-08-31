package myjava.myutils.mycollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyTestSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    setToString();
	}
	
    public static void setToString(){
        Set<String> oSet = new HashSet<String>();
        System.out.println(oSet.add("a"));
        System.out.println(oSet.add("a"));
        oSet.add("b");
        String str = String.join(", ", oSet);
        System.out.println("oSet join: " + str);
        System.out.println("oSet: " + oSet);
        System.out.println("toString: " + oSet.toString());
        
        System.out.println(oSet.toArray());
        String arr = oSet.toString().replace("[", "(").replace("]", ")");
        
        System.out.println(arr);
    }
	
	public static void setToArray(){
	    Set<Object> oSet = new HashSet<Object>();
	    oSet.add("a");
	    oSet.add("b");
	    Object[] arro = new Object[oSet.size()];
	    String[] arr = new String[oSet.size()];
	    arro = oSet.toArray();
	    //arr = (String[]) oSet.toArray();
	    System.out.println(Arrays.asList(arro));
	}
	
	public static void removeAllSet(){
	    Set<String> s1 = new TreeSet<String>();
	    s1.add("b");
	    s1.add("a");
	    Set<String> s2 = new TreeSet<String>();
	    s2.add("b");
	    s2.add("1");
	    
	    s1.removeAll(s2);
	    
	}
	

    public static void sepThondsSet(){
        List<Set<String>> dBasketLists = new ArrayList<Set<String>>();
        Set<String> dBasketTags = new LinkedHashSet<String>();
        int len = 9;
        int i = 0;
        while(i < len){
            dBasketTags.add(""+i);
            if (len > 3) {
                if (i + 1 > 0 && (i + 1) % 3 == 0) {
                    dBasketLists.add(dBasketTags);
                    dBasketTags = new LinkedHashSet<String>();
                }
                if (i > (len / 3 * 3)) {
                    dBasketLists.add(dBasketTags);
                }
            } else {
                dBasketLists.add(dBasketTags);
            }
            i++;
        }
        System.out.println(dBasketLists);
    }
    
	
	public static void testLinkedOrder(){
		Set<String> dBasketTags = new LinkedHashSet<String>();
		dBasketTags.add("1");
		dBasketTags.add("2");
		dBasketTags.add("3");
		dBasketTags.add("4");
		dBasketTags.add("null");
		dBasketTags.add("");
		dBasketTags.add("");
		System.out.println(dBasketTags.toString());
	}
	
	
	// testSetAddAll
	public static void testSetAddAll(){
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		set1.add("1");
		set1.add("3");
		set1.add("2");
		set1.add("1");
		set2.add("1");
		set2.add("3");
		set2.add("2");
		set2.add("1");
		set2.addAll(set1);
		System.out.println(set2);
	}
	
	public static void testSetRetainAll(){
	    Set<String> result = new HashSet<String>();
	    Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new HashSet<String>();
        set1.add("1");
        set1.add("3");
        set1.add("2");
        set1.add("1");
        set2.add("1");
        set2.add("3");
        set2.add("2");
        set2.add("8");
        result.addAll(set1);
        result.retainAll(set1);
        result.retainAll(set2);
        System.out.println(result);
	}
	
}
