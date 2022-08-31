package myalgorithms.mysort;

import java.util.Random;

public class MySortUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = createRandomArr();
        show(arr);
        exch(arr, 0, 1);
        show(arr);
    }
    
    public static int[] createRandomArr() {
        return createRandomArr(10);
    }
    
    public static int[] createRandomArr(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }
    
    public static void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    
    
    public static boolean less(int i, int j) {
        return i < j;    
    }
    
    public static void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;        
    }
    
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                System.out.println("not sorted");
                return false;
            }
        }
        System.out.println("sorted");
        return true;
    }
    
    
}
