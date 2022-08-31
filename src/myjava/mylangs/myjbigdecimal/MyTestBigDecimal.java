package myjava.mylangs.myjbigdecimal;

import java.math.BigDecimal;

public class MyTestBigDecimal {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        decimalDouble();
    }
    
    public static void decimalDouble(){
        double res0 = 1.02;
        BigDecimal price1 = new BigDecimal(res0);
        double double1 = 1.020000000000000017763568394002504646778106689453125;
        System.out.println(res0+"double bigdecimal"+double1);
        double decimal = double1 - res0;
        double d1 = 0.005;
        System.out.println(d1/decimal);
        
    }
    
}
