package myjava.myutils.mycollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;



public class MyTestList {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        forEachList();
    }

    public static void forEachList() {
        List<Student> sList = new ArrayList<Student>();
        for (Student student : sList) {
            System.out.println(student);
        }
    }

    public static void changeListObj() {
        Student student = new Student("a");
        List<Student> sList = new ArrayList<Student>();
        sList.add(student);
        System.out.println(sList.get(0).getName());
        student.setName("b");
        System.out.println(sList.get(0).getName());
    }
    
    public static void removeListTest() {
        List<String> testList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            testList.add(i+"");
        }
        System.out.println(testList);
        for (int j = 2; j < 5; j++) {
            testList.remove(j);
        }
        System.out.println(testList);
    }
    
    public static void multiThreadAddTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println("=============="+i+"==============");
            multiThreadAdd();
        }
    }

    /**
     * 多线程 ArrayList.add
     */
    public static void multiThreadAdd() {
        //final ArrayList<Student> studentList = new ArrayList<Student>();
        final List<Student> studentList = Collections.synchronizedList(new ArrayList<Student>());
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addStudentList(studentList, finalI);
                }
            }
            ).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printList(studentList);
    }

    //public static synchronized void addStudentList(ArrayList<Student> studentList, int finalI) {
    public static void addStudentList(List<Student> studentList, int finalI) {
        Student student = new Student(null);
        student.setName("name" + finalI);
        studentList.add(student);
    }
    
    public static void printList(List<Student> studentList) {
        System.out.println(studentList.size());
        for (Student student : studentList) {
            if (null == student) {
                System.out.println("值" + student);
            }
        }
    }
    
    public static void lastListValue(){
        List<String> sList = new ArrayList<String>();
        sList.add("a");
        sList.add("b");
        sList.add("c");
        System.out.println(sList.get(sList.size()-1));
    }
    
    public static void idxListValue(){
        List<String> sList = new ArrayList<String>();
        sList.add("IIP=NA");
        
    }
    
    public static void changeListValue(){
        List<Student> sList = new ArrayList<Student>();
        sList.add(new Student("a"));
        sList.add(new Student("b"));
        for (Student student : sList) {
            System.out.println(student.getName());
        }
        
        for (Student student : sList) {
            student.setName("*");
        }
        for (Student student : sList) {
            System.out.println(student.getName());
        }
        
    }
    
    public static void nullListForEach(){
        List<String> messageSelectorListSub = null;
        messageSelectorListSub = new ArrayList<String>();
        for (String messageSelector : messageSelectorListSub) {
            System.out.println("hh null list");
        }
    }

    
    public static void subFutureEachNum(){
        String messageSelector = "exchId in (";
        List<String> messageSelectorList = new ArrayList<String>();
        Set<String> futureQuotaSet = new HashSet<String>();
        futureQuotaSet.add("Dfb2008");
        /*futureQuotaSet.add("Dfb2009");
        futureQuotaSet.add("Dfb2011");*/
        
        Set<String> exchIdQuotaSet = new HashSet<String>();
        Set<String> stkIdQuotaSet = new HashSet<String>();

        // 订阅计数
        int count = 0;
        // 各个订阅集合取并集
        for (String key : futureQuotaSet) {
            count++;
            exchIdQuotaSet.add("'" + key.substring(0, 1) + "'");
            stkIdQuotaSet.add("'" + key.substring(1, key.length()) + "'");
            int eachNum = 2;
            int eachCount = count / eachNum;
            if ((eachCount > 0 && count % eachNum == 0)
                    || ((count == futureQuotaSet.size()) && (count - eachCount*eachNum < eachNum))) {
                messageSelector = transSubInfoToMsgSelector(exchIdQuotaSet, stkIdQuotaSet);
                messageSelectorList.add(messageSelector);
                messageSelector = "";
                exchIdQuotaSet.clear();
                stkIdQuotaSet.clear();
            }
        }
        
        futureQuotaSet.clear();
        
        /*// 订阅数不足每次限制数
        if (messageSelectorList.size() == 0) {

            messageSelectorList.add(messageSelector);
        }*/
        System.out.println(messageSelectorList);
    }
        
    /**
     * 
     * @param exchIdQuotaSet ['F']
     * @param stkIdQuotaSet ['IF2009', 'IF2003']
     * @return exchId in ('F') AND stkId in ('IF2009', 'IF2003')
     */
    private static String transSubInfoToMsgSelector(Set<String> exchIdQuotaSet, Set<String> stkIdQuotaSet) {
        StringBuilder msgSec = new StringBuilder();
        String exchIdStart = "exchId in (";
        String stkIdStart = " AND stkId in (";
        String end = ")";
        if (null != exchIdQuotaSet
            && exchIdQuotaSet.size() > 0
            && null != stkIdQuotaSet
            && stkIdQuotaSet.size() > 0) {
            String exchIds = exchIdQuotaSet.toString().replace("[", "").replace("]", "");
            String stkIds = stkIdQuotaSet.toString().replace("[", "").replace("]", "");
            msgSec
                .append(exchIdStart)
                .append(exchIds)
                .append(end)
                .append(stkIdStart)
                .append(stkIds)
                .append(end);
        }
        return msgSec.toString();
    }
    
    public static void subStockEachNum(){
        List<String> messageSelectorList = new ArrayList<String>();
        String messageSelector = "";
        Set<String> stockQuotaSet = new TreeSet<String>();
        Set<String> stkIdQuotaSet = new HashSet<String>();
        stockQuotaSet.add("000001");
        stockQuotaSet.add("600000");
        /*stockQuotaSet.add("688008");
        stockQuotaSet.add("699558");*/
        // 订阅计数
        int count = 0;
        int eachNum = 2;
        int stockLen = stockQuotaSet.size();
        // 各个订阅集合取并集
        for (String key : stockQuotaSet) {
            count++;
            // 已处理批次
            int eachCount = count / eachNum;

            stkIdQuotaSet.add("'" + key + "'");
            if ((eachCount > 0 && count % eachNum == 0)
                    || ((count == stockLen) && (count - eachCount * eachNum < eachNum))) {
                messageSelector = transSubInfoToMsgSelector(stkIdQuotaSet);
                messageSelectorList.add(messageSelector);
                messageSelector = "";
                stkIdQuotaSet.clear();
            }
        }
        stockQuotaSet.clear();
        
        for (String string : messageSelectorList) {
            System.out.println(string);
        }
    }
    
    /**
     * 
     * @param stkIdQuotaSet ['362086', '360726']
     * @return stkId in ('362086', '360726')
     */
    private static String transSubInfoToMsgSelector(Set<String> stkIdQuotaSet) {
        StringBuilder msgSec = new StringBuilder();
        String stkIdStart = " stkId in (";
        String end = ")";
        if (null != stkIdQuotaSet
            && stkIdQuotaSet.size() > 0) {
            String stkIds = stkIdQuotaSet.toString().replace("[", "").replace("]", "");
            msgSec.append(stkIdStart)
                .append(stkIds)
                .append(end);
        }
        return msgSec.toString();
    }
    
    public static void origListLen(){
        List<String> arr = new ArrayList<String>();
        int arrLen = arr.size();
        System.out.println(arrLen);
    }
    
    public static void removeGetNull(){
        List<Integer> listA = new ArrayList<Integer>();
        listA.add(16516);
        listA.add(16517);
        listA.add(16538);
        listA.add(16545);
        listA.add(16480);
        System.out.println(listA);
        listA.remove(0);
        System.out.println(listA);
        System.out.println(listA.get(0));
    }
    
    public static void removeList(){
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
        int size = listA.size();
        System.out.println(listA);
        listA.remove(0);
        System.out.println(listA);
        int size1 = listA.size();
        System.out.println(size + " " + size1);
        
    }
    
    public static void falTruSet(){
        Set<String> qsSet = new HashSet<String>();
        boolean a = qsSet.add("a");
        boolean a1 = qsSet.add("a");
        boolean b = qsSet.add("b");
        System.out.println(a);
        System.out.println(a1);
        System.out.println(b);
    }
    
    public static void sortList(){
        
        String[] perStrings = new String[]{"./log/PerformanceLog.log","./log/PerformanceLog.log.1","./log/PerformanceLog.log.10","./log/PerformanceLog.log.11","./log/PerformanceLog.log.2","./log/PerformanceLog.log.3","./log/PerformanceLog.log.4","./log/PerformanceLog.log.5","./log/PerformanceLog.log.6","./log/PerformanceLog.log.7","./log/PerformanceLog.log.8","./log/PerformanceLog.log.9"};
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(perStrings);
        Set<String> set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareTo(o1, o2);   //设计的功能方法
            }
        });
        set.addAll(list);
        List<Object> temp = new LinkedList(); 
        temp = Arrays.asList(set.toArray());
        Collections.reverse(temp);
        System.out.println(temp);
        System.out.println(Arrays.asList(set.toArray()));
    }
    
    public static int getNum(String sb){
        if(sb == null){
            return 0;
        }
        if("".equals(sb)){
            return 0;
        }
        return Integer.valueOf(sb);
    }

    public static boolean isNum(char c){
        boolean b = false;
        if(c >= '0' && c <= '9'){
            b = true;
        }
        return b;
    }

    public static boolean isChar(char c){
        boolean b = false;
        if((c >= 'a' && c <= 'z' )|| ( c >= 'A' && c <= 'Z')){
            b = true;
        }
        return b;
    }

    public static int compareTo(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int lim = Math.min(len1, len2);
        char v1[] = s1.toCharArray();
        char v2[] = s2.toCharArray();
        char int1[] = null;
        char int2[] = null;

        int k = 0;
        // 外层循环用于遍历两个字符串
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            // 如果遍历到两个字符串相同位置都是数字, 那么就要去把这个数字后面连续的数字都挖出来
            if(isNum(c1) && isNum(c2)){
                int1 = new char[len1 - k];
                int2 = new char[len2 - k];
                int count1 = 0;
                int count2 = 0;
                int n1,n2;

                //此循环用于挖出 s1 数字, 依次放到预先的int1[] 数组中
                for (int i=k;i<len1;i++){
                    if(isNum(v1[i])){
                        int1[count1] = v1[i];
                    }else {
                        break;
                    }
                    count1++;
                }

                //此循环用于挖出 s2 的数字, 依次放到预先的int2[] 数组中
                for (int i=k;i<len2;i++){
                    if(isNum(v2[i])){
                        int2[count2] = v2[i];
                    }else {
                        break;
                    }
                    count2++;
                }

                n1 =getNum(String.valueOf(int1).trim());
                n2 =getNum(String.valueOf(int2).trim());

                // 需要判断n1 和 n2 是否相等, 如果相等, 则先把 k 移位, 然后continue
                if(n1 == n2){
                    k += int1.length;
                    continue;
                }

                //如果不相等, 那么就成功得到了想要的结果
                return n1 - n2;
            }

            // 如果遍历的两个字符不是全为数字的话, 就直接比较, 得出结果
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}

class Student{
    private String name;
    
    public Student() {
        super();
    }
    
    public Student(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + "]";
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
}