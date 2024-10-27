package cn.willian.coding.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-07 00:24:43
 */
@Slf4j
public class ReadWriteLockDemo {

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        new Thread(ReadWriteLockDemo::read, "A").start();
        new Thread(ReadWriteLockDemo::read, "B").start();
        new Thread(ReadWriteLockDemo::write, "C").start();
    }

    private static void read() {

        String name = Thread.currentThread().getName();
        readLock.lock();
        try {
            log.info("线程{}正在读取数据....", name);
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            // ignore
        } finally {
            readLock.unlock();
        }
    }

    private static void write() {

        String name = Thread.currentThread().getName();
        writeLock.lock();
        try {
            log.info("线程{}正在写入数据....", name);
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            // ignore
        } finally {
            writeLock.unlock();
        }
    }
}

