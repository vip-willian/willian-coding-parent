package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 21:12:04
 */
@Slf4j
public class DeadLockDemo {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                log.info("线程A开始执行");
                synchronized (lock2) {
                    log.info("等待获取锁2");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (lock2) {
                log.info("线程B开始执行");
                synchronized (lock1) {
                    log.info("等待获取锁1");
                }
            }
        }, "B").start();
    }
}
