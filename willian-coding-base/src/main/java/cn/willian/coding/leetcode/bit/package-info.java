/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-02 12:03:06
 */
package cn.willian.coding.leetcode.bit;

// 常见位运算
// 数组大小为2次幂 取模操作 index & arr.length - 1
// n & (n - 1) 消除n后面最后一个1
// n ^ n = 0 | n ^ 0 = n 满足交换律和结合律
// n & (~n + 1) 取出最后一个1
