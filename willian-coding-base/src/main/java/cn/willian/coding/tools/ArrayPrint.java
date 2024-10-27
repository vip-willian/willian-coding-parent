package cn.willian.coding.tools;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-17 09:37:33
 */
public class ArrayPrint {

    public static void print(int[] nums) {

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
}
