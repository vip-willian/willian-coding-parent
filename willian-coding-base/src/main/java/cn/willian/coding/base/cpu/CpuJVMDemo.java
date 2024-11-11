package cn.willian.coding.base.cpu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-29 12:24:26
 */
public class CpuJVMDemo {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final Object lock = new Object();

    public static Runnable task = () -> {
        synchronized (lock) {
            long sum = 0L;
            while (true) {
                sum += 1;
            }
        }
    };

    public static void main(String[] args) {
        executorService.submit(task);
    }
}
