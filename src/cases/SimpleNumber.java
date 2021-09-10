package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleNumber {
    public static void main(String[] args) {
        ArrayList<Integer>list = new ArrayList<>(100);
        for (int i = 2; i < 100; i++) {
            list.add(i);
        }
        list.forEach(s1 -> System.out.println(s1 + " is simple number " + isSimpleNumber(s1)));
        System.out.println(Eratosfen( 100));

    }

    public static boolean isSimpleNumber(int n) {
        boolean isSimpleNumber = true;
        for (int i = 2; i*i < n - 1; i++) {
            if (n % i == 0) {
                isSimpleNumber = false;
                break;
            }
        }
        return isSimpleNumber;
    }
    public static List<Integer> Eratosfen (int max) {
        boolean [] isPrime = new boolean[max];
        Arrays.fill(isPrime, true);
        for (int i = 0; i*i < max; i++) {
            if (isPrime[i]){
                for (int j = 2*i; j <max ; j = j + i) {
                    isPrime[j] = false;
                    
                }
            }

        }
        return new ArrayList<>();
    }

}
