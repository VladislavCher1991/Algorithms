package sort;

import java.util.Arrays;

public class CalculateSort {

    static int[] arr = new int[]{16, 32, 45, 86, 34, 12, 9, 37, 99, 10, 94, 72, 64, 54, 10};

    public static void main(String[] args) {
        calculateSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void calculateSort(int[] arr) {
        final int MAX_VALUE =100;
        int[] count = new int[MAX_VALUE];
        for (int k : arr) {
            count[k] = count[k] + 1;
        }

        int arrIndex = 0;
        for (int i = 0; i < MAX_VALUE; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[arrIndex] = i;
                arrIndex++;
            }
        }
    }
}
