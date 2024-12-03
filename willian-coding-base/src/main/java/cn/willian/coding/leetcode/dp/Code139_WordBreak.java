package cn.willian.coding.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 15:17:39
 */
// 139. 单词拆分
// https://leetcode.cn/problems/word-break/description
public class Code139_WordBreak {

    public static void main(String[] args) {

        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak1(s, wordDict));
        System.out.println(wordBreak2(s, wordDict));
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {

        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        HashSet<String> words = new HashSet<>(wordDict);
        // 字符串长度
        int n = s.length();
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return dfs(n, maxLength, s, words, cache) == 1;
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {

        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        HashSet<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            // 如果选择的词汇已经超过wordDict词汇中的最大一个，没必要再继续
            for (int j = i - 1; j >= Math.max(i - maxLength, 0); j--) {
                if (words.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // 整体分析: leetcode 是否包含在words等于 e是否在words中
    private static int dfs(int i, int maxLength, String s, HashSet<String> words, int[] cache) {

        // 递归到空串，说明存在解
        if (i == 0) {
            return 1;
        }
        if (cache[i] != -1) {
            return cache[i];
        }
        // 将字符串进行拆分判断
        // 如果选择的词汇已经超过wordDict词汇中的最大一个，没必要再继续

        for (int j = i - 1; j >= Math.max(i - maxLength, 0); j--) {
            if (
            // 如果字符串在最后一个位置上满足
            words.contains(s.substring(j, i)) &&
            // 检查前面的字符串是否在words中包含
                dfs(j, maxLength, s, words, cache) == 1) {
                return cache[i] = 1;
            }
        }
        return cache[i] = 0;
    }
}
