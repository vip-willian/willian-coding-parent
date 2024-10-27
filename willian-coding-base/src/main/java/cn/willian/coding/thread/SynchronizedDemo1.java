package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 14:13:05
 */
@Slf4j
public class SynchronizedDemo1 {

    public static void main(String[] args) {

        SynchronizedDemo1 demo1 = new SynchronizedDemo1();
        new Thread(demo1::method3, "A").start();
        new Thread(demo1::method4, "B").start();

        new Thread(SynchronizedDemo1::method1, "C").start();
        new Thread(SynchronizedDemo1::method2, "D").start();
    }

    // 类锁，锁的是SynchronizedDemo1.class
    public static synchronized void method1() {
        log.info("开始执行method1,锁的是类对象(SynchronizedDemo1.class)");
    }

    // 类锁，锁的是SynchronizedDemo1.class
    public static void method2() {
        synchronized (SynchronizedDemo1.class) {
            log.info("开始执行method2,锁的是类对象(SynchronizedDemo1.class)");
        }
    }

    // this锁，锁的是当前实例对象
    public synchronized void method3() {
        log.info("开始执行method3,锁的是this,SynchronizedDemo1的实例");
    }

    // this锁，锁的是当前实例对象
    public void method4() {
        synchronized (this) {
            log.info("开始执行method4,锁的是this,SynchronizedDemo1的实例");
        }
    }
}
