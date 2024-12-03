package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-02 10:14:47
 */
public class ConvertToLetterString {

    public static void main(String[] args) {
        String str = "213422543";
        int result = process(str.toCharArray(), 0);
        System.out.println(result);
    }

    public static int process(char[] str, int i) {

        int length = str.length;
        if (i == length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        // 第一种情况，str[i] 位置 == 1
        if (str[i] == '1') {
            // 在i位置上做选择
            int res = process(str, i + 1);
            // i和i+1联合选择
            if (i + 1 < length) {
                res += process(str, i + 2);
            }
            return res;
        }
        // 第二种情况，str[i] 位置 == 2 字母要小于26
        if (str[i] == '2') {
            // 在i位置上做选择
            int res = process(str, i + 1);
            // i+1位置上没有大于6的话，可以i和i+1联合选择
            if (i + 1 < length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                res += process(str, i + 2);
            }
            return res;
        }
        // 第三种情况，str[i] 位置 > 2 只能在i位置上做选择
        return process(str, i + 1);
    }
}
