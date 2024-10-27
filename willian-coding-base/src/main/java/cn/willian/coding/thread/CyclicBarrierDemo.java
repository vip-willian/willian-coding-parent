package cn.willian.coding.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 21:49:15
 */
@Slf4j
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        int threadNum = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            log.info("三个线程已经都到了同一个执行点");
        });

        for (int i = 0; i < threadNum; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    log.info("线程{}到达栅栏", threadId);
                    cyclicBarrier.await();
                    log.info("线程{}冲破栅栏", threadId);

                    Thread.sleep(1000);

                    log.info("线程{}再次到达栅栏", threadId);
                    cyclicBarrier.await();
                    log.info("线程{}再次冲破栅栏", threadId);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, "线程" + i).start();
        }
    }
}
