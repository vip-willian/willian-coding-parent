package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 12:00:05
 */
// https://leetcode.cn/problems/palindrome-partitioning/
// 131. 分割回文串
// 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串 返回 s 所有可能的分割方案。
//
//示例 1：
//
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
//示例 2：
//
//输入：s = "a"
//输出：[["a"]]
public class SplitPalindromeStr {

    private static final List<List<String>> RESULT = new ArrayList<>();
    private static final LinkedList<String> PATH = new LinkedList<>();

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = partition(s);
        System.out.println(result);
    }

    public static List<List<String>> partition(String s) {

        if (null == s || s.length() == 0) {
            return RESULT;
        }
        backtracking(s, 0);
        return RESULT;
    }

    private static void backtracking(String s, int startIndex) {

        if (startIndex == s.length()) {
            RESULT.add(new ArrayList<>(PATH));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String subStr = s.substring(startIndex, i + 1);
            if (!isPalindrome(subStr)) {
                continue;
            }
            PATH.add(subStr);
            backtracking(s, i + 1);
            PATH.removeLast();
        }
    }

    private static boolean isPalindrome(String subStr) {

        for (int i = 0; i < subStr.length() / 2; i++) {
            if (subStr.charAt(i) != subStr.charAt(subStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
