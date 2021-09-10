package cases;

import java.util.*;

public class GreedyModel {
    public static void main(String[] args) {

        int[] digits = fullArray(100000);
        Date date1 = new Date();
        System.out.println(maxNumberFromDigits(digits));
        System.out.println(alterWay(digits));
        Date date2 = new Date();
        System.out.println((date2.getTime() - date1.getTime()) / 1000);
    }

    public static String maxNumberFromDigits(int[] digits) {
        int max = 0;
        int index = 0;
        boolean isFinish = false;
        StringBuilder builder = new StringBuilder();
        while (!isFinish) {
            isFinish = true;
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] == -1) continue;
                if (max < digits[i]) {
                    max = digits[i];
                    index = i;
                    isFinish = false;
                }

            }
            if (!isFinish) {
                builder.append(max);
                max = 0;
                digits[index] = -1;
            }
        }
        return builder.toString();
    }

    public static String alterWay(int[] digits) {
        return String.join("", Arrays.stream(digits).boxed()
                .sorted(Collections.reverseOrder())
                .map(String::valueOf)
                .toArray(String[]::new));
    }

    public static int[] fullArray(int length) {
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Random().nextInt(10);
        }
        return result;
    }
}
