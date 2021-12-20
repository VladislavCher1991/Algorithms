package sort;

import java.util.Arrays;

public class SortThroughMin {

    static int[] arr = new int[]{16, 32, 45, 86, 34, 12, 9, 37, 99, 10, 94, 72, 64, 54, 10};

    public static void main(String[] args) {
        chooseSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] minOrMaxValue(int[] arr, int from, int to) {
        int[] result = new int[2];
        int minIndex = from;
        int maxIndex = to;
        int min = arr[from];
        int max = arr[to];


        for (int i = from; i <= to; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }

            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }
        result[0] = minIndex;
        result[1] = maxIndex;
        return result;
    }

    public static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int[] minOrMaxIndex = minOrMaxValue(arr, i, arr.length - i - 1);

            int minTemp = arr[i];
            int maxTemp = arr[arr.length - i - 1];
            arr[i] = arr[minOrMaxIndex[0]];
            arr[arr.length - i - 1] = arr[minOrMaxIndex[1]];
            arr[minOrMaxIndex[0]] = minTemp;
            arr[minOrMaxIndex[1]] = maxTemp;
            System.out.println(Arrays.toString(arr));
        }
    }
}