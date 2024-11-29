package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 14:00:18
 */
// https://leetcode.cn/problems/restore-ip-addresses/
// 93. 复原 IP 地址
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
//
//示例 1：
//
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
//示例 2：
//
//输入：s = "0000"
//输出：["0.0.0.0"]
//示例 3：
//
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
public class RestoreIpAddresses {

    private static final List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> result = restoreIpAddresses(s);
        System.out.println(result);
    }

    public static List<String> restoreIpAddresses(String s) {

        if(s == null || s.length() == 0) {
            return result;
        }
        result.clear();
        StringBuilder builder = new StringBuilder(s);
        backtracking(builder, 0, 0);
        return result;
    }

    private static void backtracking(StringBuilder str, int startIndex, int pointNum) {

        // 已经有3个点了，说明满足IP地址要求,可以跳出递归
        if (pointNum == 3) {
            // 如果最后一个ip地址段满足要求，则可以加入结果集
            if (isValid(str, startIndex, str.length() - 1)) {
                result.add(str.toString());
            }
            return;
        }
        for (int i = startIndex; i < str.length(); i++) {
            if (!isValid(str, startIndex, i)) {
                break;
            }
            // 处理，将点加入后面一个位置
            str.insert(i + 1, ".");
            // 处理后续的字符串段，缩小范围
            backtracking(str, i + 2, pointNum + 1);
            // 回溯
            str.deleteCharAt(i + 1);
        }
    }

    private static boolean isValid(StringBuilder str, int startIndex, int endIndex) {

        if (startIndex > endIndex) {
            return false;
        }
        if (str.charAt(startIndex) == '0' && startIndex != endIndex) {
            return false;
        }
        int num = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            num = num * 10 + str.charAt(i) - '0';
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

}
