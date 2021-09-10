package sort;

import java.util.Arrays;

public class MergeSort {
    static int[] arr = new int[]{16, 32, 45, 86, 34, 12, 9, 37, 99, 10, 94, 72, 64, 54, 10};

    public static void main(String[] args) {
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[] arr1, int arr1Start, int[] arr2, int arr2Start,
                             int[] arrResult, int arrResultStart, int size) {
        int index1 = arr1Start;
        int index2 = arr2Start;
        int arr1End = Math.min(arr1Start + size, arr1.length);
        int arr2End = Math.min(arr2Start + size, arr2.length);
        int iterator = arr1End + arr2End - arr1Start - arr2Start;
        for (int i = arrResultStart; i < arrResultStart + iterator; i++) {
            if (index1 < arr1End && (index2 >= arr2End || arr1[index1] < arr2[index2])) {
                arrResult[i] = arr1[index1];
                index1++;
            } else {
                arrResult[i] = arr2[index2];
                index2++;
            }
        }
    }

    public static void mergeSort(int[] arr) {
        int[] temp;
        int[] currentArr = arr;
        int[] resultArr = new int[arr.length];
        int size = 1;
        while (arr.length > size) {
            for (int i = 0; i < arr.length; i += 2 * size) {
                merge(currentArr, i, currentArr, i + size, resultArr, i, size);
            }
            temp = currentArr;
            currentArr = resultArr;
            resultArr = temp;

            size = size *  2;
            System.out.println(Arrays.toString(currentArr));
        }
    }
}
