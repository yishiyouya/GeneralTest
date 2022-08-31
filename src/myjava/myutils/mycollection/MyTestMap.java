package myjava.myutils.mycollection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import myjava.mypojo.MyStudent;
import edu.emory.mathcs.backport.java.util.Collections;


public class MyTestMap {

	public static void main(String[] args) {
	    testPut();
	}

	public static void testPut(){
	    Map<String, String> map = new HashMap<String, String>();
	    String res = "";
	    res = map.put("a", "1");
        System.out.println(null == res);
	    res = map.put("a", "2");
	    System.out.println(res);
	}
	
	public static void addValMap(){
	    Map<Student, String> map = new HashMap<Student, String>();
	    Student s1 = new Student("a");
	    Student s11 = new Student("a");
	    Student s2 = new Student("b");
	    map.put(s1, "1");
	    map.put(s11, "11");
	    map.put(s2, "2");
	    System.out.println(s1.hashCode());
	    System.out.println(s11.hashCode());
	    System.out.println(map.get(s1));
	    System.out.println(map);
	}
	
	public static void testFirstTreeMap(){
	    TreeMap<String, String> testTreeMap = new TreeMap<String, String>();
	    System.out.println(null == testTreeMap.firstEntry());
	}
	
    public static void interalCurtHashMap(){
	    ConcurrentHashMap<String, String> orderMap = new ConcurrentHashMap<String, String>();
	    orderMap.put("SID_1000000001", "a");
	    orderMap.put("SID_1000000002", "c");
	    orderMap.put("SID_1000000003", "c");
	    orderMap.put("SID_1000000004", "c");
	    for (int i = 0; i < 10; i++) {
	        System.out.println("===========\n");
	        for (Entry<String, String> entry : orderMap.entrySet()) {
	            System.out.println(entry.getKey().hashCode() + "; " + entry.getKey() + "; " + entry.getValue());
	        }
        }
	    System.out.println(null == orderMap.get("ABC"));
	}
	
    public static void orderLinkedMap(){
	    Map<String, String> map0 = new HashMap<String, String>();
	    map0.put("db", "b");
	    map0.put("abc", "b");
	    map0.put("3", "b");
	    System.out.println(map0);
	    
	    Map<String, String> map = new LinkedHashMap<String, String>();
	    map.put("db", "b");
        map.put("abc", "b");
        map.put("3", "b");
	    System.out.println(map);
	}
	
	public static void classValMap(){
	    MapValue mValue = new MapValue();
	    Map<String, String> map = new HashMap<String, String>();
	    mValue.setvMap(map);
	    map.put("a", "b");
	    Object mapVal = mValue.getvMap();
	    System.out.println(mapVal);
	}
	
	public static void pareChildMap(){
	    Map<String, Parent> map = null;
	    for (Entry<String, Parent> entry : map.entrySet()) {
	        System.out.println(entry.getKey());
	        System.out.println("===");
        }
	    map = new HashMap<String, Parent>();
	    Child child = new Child("hh");
	    child.setAge(10);
	    child.setName("no one");
	    map.put(child.getTitle(), child);
	    System.out.println(map);
	}
	
	public static void getNotExistMap(){
	    Map<String, String> inforLibMap = new HashMap<String, String>();
        inforLibMap.put("c", "3");
        String res = inforLibMap.get("b");
        boolean nulStr = "null".equals(res);
        boolean nul = null == res;
        System.out.println(res+"\t"+nul);
	}
	
	public static void sortMap(){
	    Map<String, String> inforLibMap = new HashMap<String, String>();
	    inforLibMap.put("c", "3");
	    inforLibMap.put("a", "1");
	    inforLibMap.put("b", "2");
	    /*List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(inforLibMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        inforLibMap.clear();
        for (Map.Entry<String, String> entry : list) {
            inforLibMap.put(entry.getKey(), entry.getValue());
        }
        */
        
	    //
	    int[] intA = {16516, 16517, 16538, 16545,16480, 16568, 16577,16415, 16486, 16564, 16447, 16578};
	    List<Integer> listA = new ArrayList<Integer>();
	    listA.add(16516);
	    listA.add(16517);
	    listA.add(16538);
	    listA.add(16545);
	    listA.add(16480);
	    listA.add(16568);
	    listA.add(16577);
	    listA.add(16415);
	    listA.add(16486);
	    listA.add(16564);
	    listA.add(16447);
	    listA.add(16578);
	    Collections.sort(listA, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
	    System.out.println(listA);
	    //int
	    
	    
        List<String> list = new ArrayList<String>(inforLibMap.keySet());
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Map<String, String> inforLibMapTemp =  new HashMap<String, String>();
        
        for (String str : list) {
            inforLibMapTemp.put(str, inforLibMap.get(str));
        }
        
        System.out.println(inforLibMapTemp);
	}
	
	public static void mapSize(){
	    Map<Integer, Integer> byteMap = new HashMap<Integer, Integer>((int) Math.pow(10, 5));
	    byteMap.put(1, 3);
	    int size = byteMap.size();
	    System.out.println(size);
	    
	}
	
	public static void sortObjectMap(){
	    MyStudent ms1 = new MyStudent("c", 10);
	    MyStudent ms2 = new MyStudent("b", 15);
	    MyStudent ms3 = new MyStudent("a", 30);
	    MyStudent ms4 = new MyStudent("d", 20);
	    
	    TreeMap<MyStudent, String> sMap = new TreeMap<MyStudent, String>(new Comparator<MyStudent>() {
	        @Override
	        public int compare(MyStudent ms1, MyStudent ms2){
	            return ms2.getName().compareTo(ms1.getName());
	        }
        });
	    sMap.put(ms1, ms1.getName());
	    sMap.put(ms2, ms2.getName());
	    sMap.put(ms3, ms3.getName());
	    sMap.put(ms4, ms4.getName());
	    System.out.println(sMap.firstEntry());
	    System.out.println(sMap);
	}
	
	public static void mapGetSortValues(){
        Map<String, String> map = mapRetSortValues();
        System.out.println("returned:"+map.values());
    }
	
	public static Map<String, String> mapRetSortValues(){
        Map<String, String> map = null;
        map = new LinkedHashMap<String, String>();
        for (int i = 0; i < 5; i++) {
            map.put(i+"", 10-i+"");
        }
        Iterator<String> itOptList = map.values().iterator();
        while (itOptList.hasNext()) {
            String infoStr = itOptList.next();
            System.out.println(infoStr);
        }
        System.out.println("before:"+map.values());
        return map;
    }
	
	
	public static void testTreeMap(){
	    Map<String, String> map = new TreeMap();
	    //Map<String, String> map = new LinkedHashMap<String, String>();
	    map.put("b", "1");
	    map.put("a", "1");
	    map.put("c", "1");
	    System.out.println(map.toString());
	}
	
	
    public static void mapSortValues(){
	    HashMap<String, String> map = new HashMap<String, String>();
	    for (int i = 0; i < 5; i++) {
            map.put(i+"", 10-i+"");
        }
	    Iterator<String> itOptList = map.values().iterator();
        while (itOptList.hasNext()) {
            String infoStr = itOptList.next();
            System.out.println(infoStr);
        }
	    System.out.println(map.values());
	}
	
    public static void operaMapReture(){
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		
		System.out.println(operaMapSet(map));
	}
	
	
	public static Map<String, Set<String>> operaMapSet(Map<String, Set<String>> map){
		Set<String> set = new HashSet<String>();
		set.add("a");
		map.put("1", set);
		System.out.println(map);
		return map;
	}
	
	public static void emptyEffect(){
		Map<String, String> map = new HashMap<String, String>();
		boolean isE = map.isEmpty();
		System.out.println(isE);
	}
}

class MapValue {
    private Map<String, String> vMap;

    public Map<String, String> getvMap() {
        return vMap;
    }

    public void setvMap(Map<String, String> vMap) {
        this.vMap = vMap;
    }
}
