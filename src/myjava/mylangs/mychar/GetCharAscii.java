package myjava.mylangs.mychar;

/** 
 * Java�н�һ���ַ����ӦAscii�뻥ת 
 * 1 byte = 8bit ���Ա�ʾ 0-127 
 */  
public class GetCharAscii {  
  
    /*0-9��ӦAscii 48-57 
     *A-Z 65-90 
     *a-z 97-122 
     *��33��126��(��94��)���ַ������е�48��57��Ϊ0��9ʮ������������ 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
  
        System.out.println(charToByteAscii(''));  
        System.out.println(byteAsciiToChar(1));  
        System.out.println(SumStrAscii("19"));  
        System.out.println(SumStrAscii("һ"));  
    }  
  
    /** 
     * ����һ����char ǿ��ת��Ϊbyte 
     * @param ch 
     * @return 
     */  
    public static byte charToByteAscii(char ch){  
        byte byteAscii = (byte)ch;  
          
        return byteAscii;  
    }  
    /** 
     * ����������charֱ��ת��Ϊint����ֵ�����ַ���ascii 
     * @param ch 
     * @return 
     */  
    public static byte charToByteAscii2(char ch){  
        byte byteAscii = (byte)ch;  
          
        return byteAscii;  
    }  
    /** 
     * ͬ��asciiת��Ϊchar ֱ��intǿ��ת��Ϊchar 
     * @param ascii 
     * @return 
     */  
    public static char byteAsciiToChar(int ascii){  
        char ch = (char)ascii;  
        return ch;  
    }  
    /** 
     * ����ַ�����ASCIIֵ�� 
     * ע�⣬��������ĵĻ������һ������������byte����ʾ����ֵ�Ǹ��� 
     */  
    public static int SumStrAscii(String str){  
        byte[] bytestr = str.getBytes();  
        int sum = 0;  
        for(int i=0;i<bytestr.length;i++){  
            sum += bytestr[i];  
        }  
        return sum;  
    }  
}  
