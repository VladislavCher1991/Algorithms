package search;

import java.util.ArrayList;
import java.util.List;

public class SubStringSearch {
    static String text = "AABAABAAAAAABAABAABAABBAAAB";
    static String subString = "AABAAB";


    public static void main(String[] args) {
        System.out.println(KMPSearch(text, subString));
        System.out.println(searchSubString(text, subString));
    }

    public static int searchSubString(String text, String sample) {
        int amount = 0;
        for (int i = 0; i < text.length() - sample.length() + 1; i++) {
            boolean isSame = true;
            int firstIndex = 0;
            int lastIndex = sample.length() - 1;
            while (isSame) {
                if (firstIndex >= sample.length() || lastIndex < firstIndex) break;
                if (text.charAt(firstIndex + i) != sample.charAt(firstIndex)
                        || text.charAt(i + lastIndex) != sample.charAt(lastIndex)) isSame = false;
                firstIndex++;
            }
            if (isSame) {
                amount++;
                i += 2;
            }
        }
        return amount;
    }

    static int[] prefixFunction(String sample) {
        int[] values = new int[sample.length()];
        for (int i = 1; i < sample.length(); i++) {
            int j = 0;
            while (i + j < sample.length() && sample.charAt(j + i) == sample.charAt(j)) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }

    public static List<Integer> KMPSearch(String text, String substring) {
        List<Integer> found = new ArrayList<>();

        int[] prefFunction = prefixFunction(substring);

        int i = 0;
        int j = 0;


        while (i < text.length()) {
            if (substring.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == substring.length()) {
                found.add(i - j);
                j = prefFunction[j - 1];
            } else if (i < text.length() && substring.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = prefFunction[j - 1];
                } else {
                    i ++;
                }
            }
        }
        return  found;
    }
}
