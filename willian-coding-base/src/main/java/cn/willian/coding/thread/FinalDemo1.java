package cn.willian.coding.thread;

import java.util.Random;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 11:05:59
 */
public class FinalDemo1 {

    // 编译期常量
    final static int j = 1;
    final int i = 1;
    final int[] a = {1, 2, 3};

    // 非编译期常量
    // k的值在初始化后，无法被改变
    Random random = new Random();
    final int k = random.nextInt(10);

    public static void main(String[] args) {

        FinalDemo1 finalDemo = new FinalDemo1();

        System.out.println(finalDemo.i);
        System.out.println(finalDemo.j);
        System.out.println(finalDemo.a);
        System.out.println(finalDemo.k);
        // 编译报错
        // finalDemo.k = finalDemo.random.nextInt();
    }
}
