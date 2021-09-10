package cases;

public class SecondMinElement {
    static int[] arr = new int[]{16, 32, 45, 86, 34, 12, 9, 37, 99, 10, 94, 72, 64, 54, 10};
    public static void main(String[] args) {
        System.out.println(secondMin(arr));
    }
    public static int secondMin (int [] arr) {
        int min = arr[0];
        int secondMin = arr[1];
        if (min > secondMin) swap (min, secondMin);

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] < min){
                swap(min, secondMin);
                min = arr[i];
            }
            if (arr[i] > min && arr[i] < secondMin) secondMin = arr[i];
        }
        return secondMin;
    }
    public static void swap (int first, int second){
        int temp = first;
        first = second;
        second = temp;
    }
}
