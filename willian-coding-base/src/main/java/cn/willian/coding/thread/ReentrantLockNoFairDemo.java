package cn.willian.coding.thread;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 23:10:10
 */
@Slf4j
public class ReentrantLockNoFairDemo {

    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        ReentrantLockNoFairDemo demo = new ReentrantLockNoFairDemo();
        Thread threadA = new Thread(() -> {
            demo.method1(1000);
        }, "A");

        Thread threadB = new Thread(() -> {
            demo.method1(2000);
        }, "B");

        Thread threadC = new Thread(() -> {
            demo.method1(3000);
        }, "C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

    public void method1(long millis) {

        lock.lock();
        try {
            log.info("{}正在执行方法1,耗时:{}ms", Thread.currentThread().getName(), millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
