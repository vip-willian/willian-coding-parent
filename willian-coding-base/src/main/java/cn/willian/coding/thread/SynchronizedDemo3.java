package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 14:13:05
 */
@Slf4j
public class SynchronizedDemo3 {

    public static void main(String[] args) {

        SynchronizedDemo3 demo = new SynchronizedDemo3();
        // 初始时，monitor计数器 = 0
        new Thread(demo::method1).start();
    }

    public synchronized void method1() {
        // 可重入锁,进入方法1，monitor计数器+1 = 1
        log.info("执行方法1");
        method2();
        // 退出方法1，monitor计数器-1 = 0
        // 此时，唤醒其他等待阻塞的线程
    }

    public synchronized void method2() {
        // 可重入锁,进入方法2，monitor计数器+1 = 2
        log.info("执行方法2");
        method3();
        // 退出方法2，monitor计数器-1 = 1
    }

    public synchronized void method3() {
        // 可重入锁,进入方法2，monitor计数器+1 = 3
        log.info("执行方法3");
        // 退出方法3，monitor计数器-1 = 2
    }
}
