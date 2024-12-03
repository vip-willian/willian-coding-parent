package cn.willian.coding.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 22:57:08
 */
public class LowestLexicography {

    public static void main(String[] args) {
        String[] str = {"jibw","ji","jp","bw","jibw"};
        System.out.println(lowestString(str));
    }

    public static String lowestString(String[] words) {

        Arrays.sort(words, new DictComparator());
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word);
        }
        return result.toString();
    }

    public static class DictComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

}
