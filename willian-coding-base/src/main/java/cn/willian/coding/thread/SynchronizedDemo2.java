package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 14:13:05
 */
@Slf4j
public class SynchronizedDemo2 {

    Object obj = new Object();

    private static void method2() {
    }

    public void method1() {
        synchronized (obj) {
        }
        method2();
    }
}
