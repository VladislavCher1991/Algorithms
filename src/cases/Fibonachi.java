package cases;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Fibonachi {
    public static void main(String[] args) {
        int n = 100000;
        System.out.println(alter(n));
        System.out.println(fibNaive(n));
    }

    public static long fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144
    //0, 1, 2, 3, 4, 5,
    public static BigDecimal alter(int n) {
        if (n <= 1) return new BigDecimal(n+1);
        ArrayList<BigDecimal> list = new ArrayList<>(n);
        list.add(0, new BigDecimal(0));
        list.add(1, new BigDecimal(1));
        for (int i = 2; i <= n; i++) {
            list.add(i, list.get(i-1).add(list.get(i-2)));
        }
        return list.get(n);
    }
}