package cn.willian.coding.thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-07 10:47:01
 */
public class CachedData {

    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Object data;
    volatile boolean cacheValid;

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            // 1、先获取写锁
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = 1;
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                // 2、再获取读锁
                rwl.readLock().lock();
            } finally {
                // 3、再释放写锁
                // 锁降级
                rwl.writeLock().unlock();
                // Unlock write, still hold read
            }
        }
        try {
            use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {
        System.out.println(data);
    }
}
