package cn.willian.coding.base;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-25 23:51:15
 */
public class JVMDemo {

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    public static void demo1() {
        int i = 0;
        i = i++;
        System.out.println(i);
    }

    public static void demo2() {
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    public static void demo3() {
        int i = 0, j = 0, k = 0;
        // i = i++;
        // j = j + 1;
        k += 1;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
