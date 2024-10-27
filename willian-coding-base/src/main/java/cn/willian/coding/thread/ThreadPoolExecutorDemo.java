package cn.willian.coding.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-09 13:53:57
 */
@Slf4j
public class ThreadPoolExecutorDemo {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 3, 0, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2), (r) -> new Thread(r, "测试线程"), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        THREAD_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 5; i++) {
            final int count = i;
            // 总任务数量
            THREAD_POOL_EXECUTOR.submit(() -> {
                // 开始执行任务1
                log.info("开始执行任务{}", count);
            });
        }
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
