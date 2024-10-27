package cn.willian.coding.thread;

import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 21:21:32
 */
@Slf4j
public class CountDownLatchDemo {

    private static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            log.info("线程1已准备完成");
            countDownLatch.countDown();
        }, "线程1").start();

        new Thread(() -> {
            log.info("线程2已准备完成");
            countDownLatch.countDown();
        }, "线程2").start();

        countDownLatch.await();

        log.info("主线程开始执行");

    }
}
