package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 18:18:48
 */
// 【没有回文串】
// 题目描述：
// 回文串Q的定义：正读和反读都一样的字符串
// 现在已经存在一个不包含回文串的字符串，字符串的字符都是在英语字母的前N个,且字符串不包含任何长度大于等于2的回文串；请找出下一个字典序的不包含回文
// 串的、字符都是在英语字母的前N个、且长度相同的字符串。如果不存在，请输出NO。
// 输入描述：
// 输入包括两行。
// 第一行有一个整数:N（1<=N<=26），表示字符串的每个字符范围都是前N的英语字母。
// 第二行输入一个字符串（输入长度<=10000），输入保证这个字符串是合法的并且没有包含回文串。
// 输出描述：
// 示例1：
// 输入
// 3
// cba
// 输出
// NO
// 对于N=3，使用字母’a’、‘b’、‘c’。
// "cba"是所有可能字符串中的最后一个，没有更高的字典序字符串满足条件。

// 示例2：
// 输入
// 2
// ab
// 输出
// ba
// N=2，使用字母’a’、‘b’。
// 下一个字典序字符串是"ba"，不包含任何回文子串。
public class NotPalindromeString {

    /**
     * 解题思路：<br>
     * 1、字典序下一个字符串生成：<br>
     * 1.1、从字符串的末尾开始，尝试在每个位置的字符增加1，直到使用完所有可能的字符（前N个字母）<br>
     * 1.2、在尝试增加字符是，确保新字符不会与前一个字符或前两个字符相同，以避免形成回文子串（长度为2或3的回文）<br>
     * 1.3、如果当前位置的字符被增加，之后的位置需要被设置为最小可能的字符（‘a’）,并且同样确保这些位置不会形成回文<br>
     * 2、回文子串检查：<br>
     * 2.1、对于生成的候选字符串，检查是否存在任何长度大于等于2的回文子串。<br>
     * 2.2、使用双指针从外向内检查子串是否回文。<br>
     * 3、终止条件： <br>
     * 3.1、如果找到了一个满足条件的下一个字符串,立即返回。<br>
     * 3.2、如果遍历完所有可能的字符后仍未找到满足调价你的字符串，返回“NO”<br>
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int N = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            String nextStr = findNextStr(str, N);
            System.out.println(nextStr);
        }
        sc.close();
    }

    /**
     * 寻找下一个不包含回文淄川的字典序字符串
     * 
     * @param str 当前字符串，保证不包含任何回文子串
     * @param N 使用的英文字母数量 (从 'a' 开始)
     * @return 下一个满足条件的字符串，如果不存在返回“NO”
     */
    private static String findNextStr(String str, int N) {

        char[] chars = str.toCharArray();
        int length = chars.length;

        // 从字符串的末尾开始尝试增加字符
        for (int i = length - 1; i >= 0; i--) {
            // 尝试将当前位置的字符串增加1到N个字母
            for (char c = (char)(chars[i] + 1); c < 'a' + N; c++) {
                // 检查c字符是否可以放在当前位置，不形成回文
                if (isValid(chars, i, c)) {
                    // 将当前位置的字符设置为c
                    chars[i] = c;
                    // 将之后的位置设置为最小可能的字符，确保字典序最小
                    for (int j = i + 1; j < length; j++) {
                        for (char next = 'a'; next < 'a' + N; next++) {
                            if (isValid(chars, j, next)) {
                                chars[j] = next;
                                break;
                            }
                        }
                    }
                    String candidate = new String(chars);
                    // 检查整个字符串是否不包含回文串
                    if (!hasPalindrome(candidate)) {
                        return candidate;
                    }
                }
            }
        }
        return "NO";
    }

    /**
     * 检查在位置index放置字符c是否会导致回文
     * 
     * @param chars 当前字符数组
     * @param index 当前要放置字符的位置
     * @param c 要放置的字符
     * @return 如果放置后不形成回文，返回true,否则返回false
     */
    private static boolean isValid(char[] chars, int index, char c) {

        // 检查与前一个字符是否相同 (避免回文长度2)
        if (index > 0 && chars[index - 1] == c) {
            return false;
        }
        // 检查与前两个字符是否相同 (避免回文长度3)
        if (index > 1 && chars[index - 2] == c) {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串是否包含任何长度大于等于2的回文子串
     * 
     * @param candidate 输入的字符串
     * @return 如果包含回文子串，返回true,否则返回false
     */
    private static boolean hasPalindrome(String candidate) {

        int n = candidate.length();
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                if (isPalindrome(candidate, i, i + len - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断子串[left....right]是否回文
     * 
     * @param candidate 输入字符串
     * @param left 左边界
     * @param right 右边界
     * @return 如果是回文，返回true ; 否则，返回false
     */
    private static boolean isPalindrome(String candidate, int left, int right) {
        while (left < right) {
            if (candidate.charAt(left) != candidate.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
