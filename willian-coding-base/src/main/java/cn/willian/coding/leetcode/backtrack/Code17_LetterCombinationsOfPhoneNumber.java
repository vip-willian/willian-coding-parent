package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 12:02:44
 */
// 17. 电话号码的字母组合
// https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description
public class Code17_LetterCombinationsOfPhoneNumber {

    private static final List<String> result = new ArrayList<>();
    private static final StringBuilder path = new StringBuilder();
    private static final String[] phoneNumbers = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        List<String> result = letterCombinations("2396");
        System.out.println(result);
    }

    public static List<String> letterCombinations(String digits) {

        result.clear();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        backtracking(0, digits);
        return result;
    }

    private static void backtracking(int index, String digits) {

        // base case
        // 当遍历到字符串最后一个位置结束，进行结果收集
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        // 获取指定数字位置的字符串
        String letters = phoneNumbers[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            // 选择当前的字符串
            path.append(letters.charAt(i));
            // 去下一个位置再进行选择
            backtracking(index + 1, digits);
            // 撤回本次的选择重新选择
            path.deleteCharAt(path.length() - 1);
        }
    }
}
