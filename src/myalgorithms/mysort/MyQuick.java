package myalgorithms.mysort;

public class MyQuick {

    public static void main(String[] args) {
        sort(MySortUtil.createRandomArr());
    }

    public static void sort(int[] arr) {
        MySortUtil.show(arr);
        sort(arr, 0, arr.length - 1);
        MySortUtil.show(arr);
        MySortUtil.isSorted(arr);
    }

    public static void sort(int[] arr, int lo, int hi) {
        // lo >= hi return 
        if (lo >= hi) {
            return;
        }
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    public static int partition(int[] arr, int lo, int hi) {
        int i = lo;
        // +1
        int j = hi + 1;
        int v = arr[lo];
        while (true) {
            while (MySortUtil.less(arr[++i], v)) {
                // == 
                if (i == hi) break;
            }

            while (MySortUtil.less(v, arr[--j])) {
                // == 
                if (j == lo) break;
            }

            if (i >= j) break;

            MySortUtil.exch(arr, i, j);
        }

        MySortUtil.exch(arr, lo, j);

        return j;
    }

}
