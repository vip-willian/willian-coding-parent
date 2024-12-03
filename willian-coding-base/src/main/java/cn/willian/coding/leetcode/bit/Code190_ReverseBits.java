package cn.willian.coding.leetcode.bit;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 11:20:13
 */
// 190. 颠倒二进制位
// https://leetcode.cn/problems/reverse-bits/description
public class Code190_ReverseBits {

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public static void main(String[] args) {
        int n = 43261596;
        Prints.printBinaryStr(n);
        int res1 = reverseBits(n);
        Prints.printBinaryStr(res1);

        int res2 = reverseBits2(n);
        Prints.printBinaryStr(res2);
    }

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {

        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            // x = n & 1 获取的是n中的最后一位
            // x << (31 - i) 将最后一个1移动指定的位数
            // | 的操作相当于拼接字符串
            res |= (n & 1) << (31 - i);
            // n往右移动一位,拿到下一位的数
            n >>>= 1;
        }
        return res;
    }

    public static int reverseBits2(int n) {

        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }
}
