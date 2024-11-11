package cn.willian.coding.leetcode;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-04 23:01:30
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
    }

    public static void hanoitower(int num, char a, char b, char c) {

        if (num == 1) {
            System.out.println("第一个盘从" + a + "->" + c);
            return;
        }
        // 将上面num - 1个盘从a先到b
        hanoitower(num - 1, a, c, b);
        // 将最后一个盘从a到c
        System.out.println("第" + num + "盘从" + a + "->" + c);
        // 将上面nums- 1个盘从b到c
        hanoitower(num - 1, b, a, c);
    }
}
