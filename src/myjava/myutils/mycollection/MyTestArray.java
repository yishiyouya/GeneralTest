package myjava.myutils.mycollection;

/**
 * 
 * Êý×é²âÊÔ
 * 
 */
public class MyTestArray {

    public static void main(String[] args){
        doubleArray();
    }
    
    public static void doubleArray(){
        int[][] arr = { {0, 1}, 
                        {2, 3}, 
                        {4, 5} };
        int[] a = arr[0];
        System.out.println(arr.length);
        System.out.println(a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
