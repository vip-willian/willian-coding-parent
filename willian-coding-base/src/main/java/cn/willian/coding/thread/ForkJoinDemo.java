package cn.willian.coding.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-05 16:37:45
 */
// RecursiveAction
// RecursiveTask
// CountedCompleter
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1,2);

        // 使用场景：

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(new CalSumForkJoinTask(1, 10000));
        Integer result = task.get();
        System.out.println(result);

    }

    public static class CalSumForkJoinTask extends RecursiveTask<Integer> {

        private static final int max = 200;
        private final int start;
        private final int end;

        public CalSumForkJoinTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            if (end - start <= max) {
                System.out.println("进行计算区间 start: " + start + " end: " + end);
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            // 拆分
            CalSumForkJoinTask sub1 = new CalSumForkJoinTask(start, (start + end) / 2);
            sub1.fork();
            CalSumForkJoinTask sub2 = new CalSumForkJoinTask(((start + end) / 2) + 1, end);
            sub2.fork();
            // 合并
            return sub1.join() + sub2.join();
        }
    }
}
