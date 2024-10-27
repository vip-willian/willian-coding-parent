package cn.willian.coding.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-10 11:09:00
 */
@Slf4j
@SuppressWarnings("all")
public class CompletableFutureDemo {

    public static void main(String[] args) {

        // runAsyncDemo();

        // supplyAsyncAndGetDemo();

        // supplyAsyncAndGetNowDemo();

        // supplyAsyncAndGetTimeOutDemo();

        // supplyAsyncErrorAndGetDemo();

        // supplyAsyncAndJoinDemo();

        // supplyAsyncErrorAndJoinDemo();

        // thenApplyAsyncDemo();
        //
        // thenAcceptAsyncDemo();

        // whenCompleteAsyncDemo();

        // applyToEitherDemo();

        // thenCombineDemo();

        // allOfDemo();

        // anyOfDemo();

        // 异步任务回调，接收上一个任务的执行结果，进行处理

        // 都有返回值
        // thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中，带有返回值。
        // thenApplyAsync/whenCompleteAsync 另外启动一个线程 ; thenApply/whenComplete 与父任务同一个线程
        // thenApply* 只获取结果，不获取异常 ; whenComplete* 获取结果、获取异常

        // 都没有返回值
        // thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中，带有返回值。
        // thenAcceptAsync/handleAsync 另外启动一个线程 ; thenAccept/handle 与父任务同一个线程
        // thenAccept* 只获取结果，不获取异常 ; handle* 获取结果、获取异常

        // 无返回值
        // 不接收上次任务结果，只能上次任务运行接收
        // thenRunAsync 另外启动一个线程 ; thenRun 与父任务同一个线程
        // 等待任务执行结果
    }

    // 无返回值
    public static void runAsyncDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // 等待task1执行完成
        task1.join();
        log.info("task1 execute finish");
    }

    // 存在返回值，未出现异常【get等待】
    public static void supplyAsyncAndGetDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
        });
        // get方法等待结果需要手动catch异常
        try {
            Integer result = task1.get();
            log.info("task1 result:{}", result);
        } catch (InterruptedException e) {
            log.error("thread:{} task1 wait InterruptedException error", Thread.currentThread(), e);
        } catch (ExecutionException e) {
            log.error("thread:{} task1 wait ExecutionException error", Thread.currentThread(), e);
        }
    }

    // 存在返回值，未出现异常【get等待】
    public static void supplyAsyncAndGetTimeOutDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
        });
        // get方法等待结果需要手动catch异常
        try {
            // 等待1秒获取执行结果
            Integer result = task1.get(3L, TimeUnit.SECONDS);
            log.info("task1 result:{}", result);
        } catch (InterruptedException e) {
            log.error("thread:{} task1 wait InterruptedException error", Thread.currentThread(), e);
        } catch (ExecutionException e) {
            log.error("thread:{} task1 wait ExecutionException error", Thread.currentThread(), e);
        } catch (TimeoutException e) {
            log.error("thread:{} task1 wait TimeoutException error", Thread.currentThread(), e);
        }
    }

    // 存在返回值，未出现异常【get等待】
    public static void supplyAsyncAndGetNowDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1 / 0;
        });
        // 运行出错或者返回为空，则使用默认值返回
        Integer result = task1.getNow(100);
        log.info("task1 result:{}", result);
    }

    // 存在返回值, 出现异常 【get等待】
    public static void supplyAsyncErrorAndGetDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1 / 0;
        });
        // get方法等待结果需要手动catch异常
        try {
            Integer result = task1.get();
            log.info("task1 result:{}", result);
        } catch (InterruptedException e) {
            log.error("thread:{} task1 wait InterruptedException error", Thread.currentThread(), e);
        } catch (ExecutionException e) {
            log.error("thread:{} task1 wait ExecutionException error", Thread.currentThread(), e);
        }
    }

    // 存在返回值，未出现异常 【join等待】
    public static void supplyAsyncAndJoinDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
        });
        Integer result = task1.join();
        log.info("task1 result:{}", result);
    }

    // 存在返回值, 出现异常 【join等待】
    public static void supplyAsyncErrorAndJoinDemo() {

        // 创建任务 supplyAsync
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1 / 0;
        });
        // 出现运行时异常
        Integer result = task1.join();
        log.info("task1 result:{}", result);
    }

    // 【存在返回值】任务1执行完成后，获取到任务1的结果后再去执行任务2,任务2执行完成后，获取到任务2的结果后再去执行任务3
    public static void thenApplyAsyncDemo() {

        // 任务1
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
            // return 1/0;
        });
        // 任务2
        CompletableFuture<Integer> task2 = task1.thenApplyAsync((result) -> {
            log.info("thread:{} start task2 do something", Thread.currentThread());
            return result + 2;
        });
        // 任务3
        CompletableFuture<Integer> task3 = task2.thenApplyAsync((result) -> {
            log.info("thread:{} start task3 do something", Thread.currentThread());
            return result * 4;
        });
        // 任务1执行失败，任务2和3，都不执行
        Integer result1 = task1.join();
        Integer result2 = task2.join();
        Integer result3 = task3.join();
        log.info("task1 result:{},task2 result:{},task3 result:{}", result1, result2, result3);
    }

    // 【不存在返回值】任务1执行完成后，获取到任务1的结果后再去执行任务2
    public static void thenAcceptAsyncDemo() {

        // 任务1
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
        });
        // 任务2
        CompletableFuture<Void> task2 = task1.thenAcceptAsync((result) -> {
            log.info("thread:{} task2 save database success result:{}", Thread.currentThread(), result);
        });
        Integer result1 = task1.join();
        task2.join();
        log.info("task1 result:{}", result1);
    }

    // 【存在返回值】返回值和任务1相同
    public static void whenCompleteAsyncDemo() {

        // 任务1
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            return 1;
            // return 1/0;
        });
        // 任务2
        CompletableFuture<Integer> task2 = task1.whenCompleteAsync((result, exception) -> {
            if (exception != null) {
                log.info("thread:{} task2 get task1 error e:{}", Thread.currentThread(), exception);
                return;
            }
            log.info("thread:{} task2 get task1 result:{}", Thread.currentThread(), result);
        });
        Integer result1 = task1.join();
        Integer result2 = task2.join();
        log.info("task1 result:{},task2 result:{}", result1, result2);
    }

    // 获取任务1、任务2 任意一个返回结果 【只要其中一个执行完了，就会执行某个指定任务】
    // 相同方法: applyToEither/applyToEitherAsync
    // 相同方法: acceptEither/acceptEitherAsync
    // 相同方法: runAfterEither/runAfterEitherAsync
    public static void applyToEitherAsyncDemo() {

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task2 do something", Thread.currentThread());
            return 2;
        });
        // 任务1或任务2返回结果,返回4
        CompletableFuture<Integer> task1OrTask2 = task1.applyToEitherAsync(task2, (result) -> {
            log.info("thread:{} get task1 or task2 result={}", Thread.currentThread(), result);
            return result * 2;
        });

        Integer result = task1OrTask2.join();
        log.info("task1OrTask2 result:{}", result);
    }

    // 获取任务1和任务2的返回结果 【只有这两个都正常执行完了，才会执行某个指定任务】
    // 相同方法: thenCombine/thenCombineAsync
    // 相同方法: thenAcceptBoth/thenAcceptBothAsync
    // 相同方法: runAfterBoth/runAfterBothAsync
    public static void thenCombineAsyncDemo() {

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 3;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task2 do something", Thread.currentThread());
            return 4;
        });
        // 任务1和任务2返回结果,返回2
        CompletableFuture<Integer> task1AndTask2 = task1.thenCombineAsync(task2, (result1, result2) -> {
            log.info("thread:{} get task1 and task2 result1={},result2={}", Thread.currentThread(), result1, result2);
            return result1 * result2;
        });
        Integer result = task1AndTask2.join();
        log.info("task1AndTask2 result:{}", result);
    }

    // 【无返回值】等待任务1和任务2执行完成
    public static void allOfDemo() {

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 任务1执行花费1秒
            int result = 3;
            log.info("thread:{} task1 execute finish result={}", Thread.currentThread(), result);
            return result;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task2 do something", Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 任务2执行花费3秒
            int result = 4;
            log.info("thread:{} task2 execute finish result={}", Thread.currentThread(), result);
            return result;
        });
        // 总耗时3秒
        CompletableFuture.allOf(task1, task2).join();
        log.info("task1 and task2 all finished");
    }

    // 【有返回值】等待任务1、任务2、任务3 任意一个执行完成
    public static void anyOfDemo() {

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task1 do something", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 任务1执行花费1秒
            int result = 3;
            log.info("thread:{} task1 execute finish result={}", Thread.currentThread(), result);
            return result;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task2 do something", Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 任务2执行花费3秒
            int result = 4;
            log.info("thread:{} task2 execute finish result={}", Thread.currentThread(), result);
            return result;
        });

        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            log.info("thread:{} start task3 do something", Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 任务2执行花费2秒
            int result = 10;
            log.info("thread:{} task3 execute finish result={}", Thread.currentThread(), result);
            return result;
        });
        // 总耗时1秒
        Object result = CompletableFuture.anyOf(task1, task2, task3).join();
        log.info("task1 or task2 or task3 finished result:{}", result);
    }
}
