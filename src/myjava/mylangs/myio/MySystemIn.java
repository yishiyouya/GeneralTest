package myjava.mylangs.myio;

import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;

public class MySystemIn {
    public static void main(String[] args){
        scanTest();
    }
    
    public static void poiTest(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle();//cell.getCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
    
    public static void scanTest(){
        Scanner sca = new Scanner(System.in);
        Scanner sca2 = null;
        String str = "";
        while(sca.hasNextLine() && !(str = sca.nextLine()).equals("3")){
            System.out.println("in "+str);
            sca2 = new Scanner(System.in);
            if(sca2.hasNextLine()){
                String str2 = sca2.next();
                System.out.println("in2 " + str2);
            }
        }
        sca.close();
        sca2.close();
        System.out.println(str);
    }
    
    
    public static void multiIn(){
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while(scanner.hasNext()){
            str += scanner.next();
        }
        System.out.println(str);
    }
    
}
