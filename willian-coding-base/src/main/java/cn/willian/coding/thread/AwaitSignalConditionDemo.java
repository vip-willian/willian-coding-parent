package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-05 23:08:36
 */
@Slf4j
public class AwaitSignalConditionDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(new Waiter()).start();
        new Thread(new Signaler()).start();
    }

    static class Waiter implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                // 等待资源
                while (!flag) {
                    log.info("资源还没准备好，需要等着");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }
                log.info("资源准备好了");
            } finally {
                lock.unlock();
            }
        }
    }

    static class Signaler implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                // 资源准备好了
                Thread.sleep(3000);
                flag = true;
                // 通知等待的线程
                condition.signalAll();
            } catch (InterruptedException exception) {
                // ignore

            } finally {
                lock.unlock();
            }
        }
    }
}
