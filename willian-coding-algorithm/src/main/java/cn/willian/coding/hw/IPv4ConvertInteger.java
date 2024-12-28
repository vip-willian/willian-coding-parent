package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-28 23:06:30
 */
// 序号3
// 题目：IPv4地址转换成整数
// 存在一种虚拟IPv4地址，由4小节组成，每节的范围为0～128，以// 号间隔，格式如下：
// （1～128）// （0～255）// （0～255）// （0～255）
// 请利用这个特性把虚拟IPv4地址转换为一个32位的整数，IPv4地址以字符串形式给出，要求每个IPv4地址
// 只能对应到唯一的整数上，如果是非法IPv4，返回invalid IP
// 输入描述：
// 输入一行，虚拟IPv4地址格式字符串
// 输出描述：
// 输出以上，按照要求输出整型或者特定字符
// 备注：输入不能确保是合法的IPv4地址，需要对非法IPv4（空串，含有IP地址中不存在的字符，非合法的
// 分十进制，十进制整数不在合法区间内）进行识别，返回特定错误
//
// 示例1：
// 输入
// 100#101#1#5
// 输出
// 1684340997
public class IPv4ConvertInteger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] arr = line.split("#");
        int len = arr.length;

        // 转换后的数字
        long result = 0;
        // 定义变量，判断是否为有效IP
        boolean flag = true;

        if (len == 4) {
            // 如何判断是有效IP地址
            for (int i = 0; i < len; i++) {
                long n = Integer.parseInt(arr[i]);
                // 0位置的数需要再 1~ 128之间
                if (i == 0 && (n < 1 || n > 128)) {
                    flag = false;
                    break;
                } else if (n < 0 || n > 255) {
                    flag = false;
                    break;
                }
                // 1、使用把IP地址分成4个数字： 128 199 231 44
                // 2、把每个数字转换为2进制
                // 3、如果转换后这个数字对应的二进制数不够8位，在左侧补0： 10000000 11000111 11100111 00101100
                result += n << (8 * (3 - i));
            }
        } else {
            flag = false;
        }

        if (flag) {
            System.out.println(result);
        } else {
            System.out.println("invalid IP");
        }

    }
}
