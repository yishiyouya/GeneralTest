package myjava.mylangs.mydouble;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import quickfix.DoubleField;

//import com.stock.businesslogic.util.Comm;

public class MyTestDouble {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        roundSelling();
    }
    
    
    
    public static void feildDouble() {
        DoubleField filed = new DoubleField(44);
        filed.setValue(12.329999999);
        System.out.println(filed.getValue());
    }
    
    public static void roundSelling(){
        BigDecimal price1 = new BigDecimal(6.517799999999999);
        BigDecimal price2 = new BigDecimal(3.512799999999999);
        double res0 = price1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(price1.doubleValue()+"四舍五入"+res0);
        double res1 = price2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(price2.doubleValue()+"四舍五入"+res1);
        
    }
    
    
    public static void multi(){
        /*double price = 6.36;
        double d = 1.02;
        double res = price*d;
        System.out.println(res);*/
        
        BigDecimal price1 = new BigDecimal(6.39);
        BigDecimal d1 = new BigDecimal(1.02);
        double res0 = price1.multiply(d1).doubleValue();
        System.out.println(res0);
        double res1 = price1.multiply(d1).setScale(2,BigDecimal.ROUND_UP).doubleValue();
        System.out.println("进位"+res1);
        double res2 = price1.multiply(d1).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println("舍位"+res2);
        
    }
    
    public static void bigDeciTest() {
        int qty = new BigDecimal(23800*0.01).intValue();
        System.out.println(qty);
    }
    
    public static void subDouble(){
        double i = 0.0000001;
        double j = 1.0000002;
        double res = 0;//Comm.doubleSubtract(i, j);
        double res1 = i - j;
        System.out.format("%s,\n%s", res, res1);
    }
    
    public static void equalDouble(){
        double rate = 0;
        
        Double.compare(rate, 0);
    }
    
    public static void eqZero() {
        double a = 0.0;
        boolean res = a == 0;
        System.out.println(res);
    }
    
    public static void forDou(){
        double d = 1.0;
        DecimalFormat format = new DecimalFormat("#");
        String str= format.format(d);
        System.out.println(Long.valueOf(str));
    }

}
