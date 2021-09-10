package sort;

import java.util.Arrays;

public class InsertSort {

    static int[] arr = new int[]{16, 32, 45, 86, 34, 12, 9, 37, 99, 10, 94, 72, 64, 54, 10};

    public static void main(String[] args) {
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort (int [] arr){
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > current){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] =current;
        }
    }
}
