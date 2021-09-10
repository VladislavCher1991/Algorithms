package search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 8, 12, 13, 15, 16, 18, 20, 22, 30, 40, 50, 55, 67};
        System.out.println(binarySearch(arr, 41, 0, arr.length - 1));
    }

    public static int binarySearch(int[] arr, int key, int low, int high) {
        int middle = low + (high - low) / 2;
        if (low > high) return -1;

        if (arr[middle] < key) {
            return binarySearch(arr, key, middle + 1, high);
        } else if (arr[middle] > key) {
            return binarySearch(arr, key, low, middle - 1);
        } else return middle;
    }
}
