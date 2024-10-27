package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 11:05:59
 */
@Slf4j
public class FinalDemo2 {

    private static FinalDemo2 finalDemo;
    private final int b;
    private int a;

    public FinalDemo2() {
        this.a = 2;
        this.b = 1;
    }

    public static void write() {
        finalDemo = new FinalDemo2();
    }

    public static void read() {
        FinalDemo2 finalDemo2 = finalDemo;
        int a = finalDemo2.a;
        log.info("读取到A的值={}", a);
        int b = finalDemo2.b;
        log.info("读取到B的值={}", b);
    }

    public static void main(String[] args) {

        new Thread(() -> FinalDemo2.write(), "A").start();

        new Thread(() -> {
            FinalDemo2.read();
        }, "B").start();
    }
}
