package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 10:01:55
 */
// https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
// 17. 电话号码的字母组合
public class CombinationPhone {

    private static final List<String> RESULT = new ArrayList<>();
    private static final StringBuilder PATH = new StringBuilder();

    public static void main(String[] args) {
        List<String> result = letterCombinations("23");
        System.out.println(result);
    }

    public static List<String> letterCombinations(String digits) {

        RESULT.clear();
        if (null == digits || digits.length() == 0) {
            return RESULT;
        }
        String[] numberString = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(digits, numberString, 0);
        return RESULT;
    }

    // digits: 原始目标
    // k: k个数
    // sum: 当前和
    // startIndex: 开始位置
    public static void backtracking(String digits, String[] numberString, int index) {

        if (index == digits.length()) {
            RESULT.add(PATH.toString());
            return;
        }
        // 获取自定字符的字母串
        String letter = numberString[digits.charAt(index) - '0'];
        for (int i = 0; i < letter.length(); i++) {
            // 选完第一个位置的数
            PATH.append(letter.charAt(i));
            // 第二个位置作选择
            backtracking(digits, numberString, index + 1);
            // 回溯
            PATH.deleteCharAt(PATH.length() - 1);
        }
    }
}
