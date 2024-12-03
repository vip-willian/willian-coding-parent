package cn.willian.coding.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-17 09:37:33
 */
public class Prints {

    /**
     * 打印数组字符串
     */
    public static void printArray(int[] nums) {

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < nums.length; i++) {
            builder.append(nums[i]);
            if (i != nums.length - 1) {
                builder.append(" , ");
            }
        }
        builder.append("]");
        System.out.println(builder);
    }

    /**
     * 打印二进制字符串
     */
    public static void printBinaryStr(String prefix, int num) {

        if (StringUtils.isNotBlank(prefix)) {
            System.out.print(prefix + " ");
        }
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
            if (i % 8 == 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * 打印二进制字符串
     */
    public static void printBinaryStr(int num) {

        printBinaryStr("", num);
    }
}
