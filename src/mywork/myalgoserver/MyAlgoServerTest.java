package mywork.myalgoserver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mywork.myjavaapi.MyFileUtil;

public class MyAlgoServerTest {

    public static void main(String[] args){
        compareQuotationFiled();
    }
    
    /**
     * 根据类字段
     * @throws IOException
     */
    public static void compareQuotationFiled() {
        Field[] mdsClassFileds = MdsApi_Quotation.class.getFields();
        Field[] algoClassFileds = AlgoServer_Quotation.class.getFields();
        
        Set<String> mdsFileds = new HashSet<String>();
        Set<String> algoFileds = new HashSet<String>();
        
        
        for (Field field : mdsClassFileds) {
            mdsFileds.add(field.getName());
        }
        
        for (Field field : algoClassFileds) {
            algoFileds.add(field.getName());
        }
        
        
        Set<String> eqFileds = new HashSet<String>();
        Set<String> notEqFileds = new HashSet<String>();
        for (String agField : algoFileds) {
            for (String mdsField : mdsFileds) {
                if (agField.equals(mdsField)) {
                    eqFileds.add(agField);
                }
            }
        }
        
        printSetFiled(eqFileds);
        algoFileds.removeAll(eqFileds);
        printSetFiled(algoFileds);
        mdsFileds.removeAll(eqFileds);
        printSetFiled(mdsFileds);
    }
    
    public static void printSetFiled(Set<String> fileds){
        for (String field : fileds) {
            System.out.print(field+",");
        }
        System.out.println();
    }
    
    /**
     * quotation类
     * mdsApi 不包含而 algoServer 包含的字段
     * @throws IOException 
     */
    public static void compareQuotationClass() throws IOException {
        
        String filePath = "E:\\lunaTestSpace\\GeneralTest\\src\\mywork\\myalgoserver\\";
        String mdsApitype = "MdsApi_";
        String algoServertype = "AlgoServer_";
        String charSet = "GBK";
        
        
        List<String> mdsApiFileds = new ArrayList<String>();
        List<String> algoServerFileds = new ArrayList<String>();
        MyFileUtil.readFile(filePath, mdsApitype, charSet);
        mdsApiFileds = MyFileUtil.getFileLines();
        
        MyFileUtil.clearFileLines();
        MyFileUtil.readFile(filePath, algoServertype, charSet);
        algoServerFileds = MyFileUtil.getFileLines();
        
        for (String alFiled : algoServerFileds) {
            
        }
        
    }
    
    
    
}
