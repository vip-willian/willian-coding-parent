package cn.willian.coding.leetcode.bit;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 11:15:32
 */
// 191. 位1的个数
// https://leetcode.cn/problems/number-of-1-bits/description
public class Code191_NumberOf1Bits {

    public static void main(String[] args) {
        int n = 2147483645;
        int result = hammingWeight(n);
        System.out.println(result);
    }

    public static int hammingWeight(int n) {

        int count = 0;
        while (n != 0) {
            // n & (n - 1) 消除最后一个1
            // 每消除一个统计一次
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
