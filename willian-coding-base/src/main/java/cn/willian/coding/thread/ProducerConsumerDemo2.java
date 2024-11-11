package cn.willian.coding.thread;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 ReentrantLock()/notifyAll() 生产者-消费者
 *
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 09:46:05
 */
@Slf4j
public class ProducerConsumerDemo2 {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition producerFullCond = lock.newCondition();
    private static final Condition consumerEmptyCond = lock.newCondition();

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        int maxCapacity = 3;

        // 开启一个生产者线程生产
        new Thread(new Producer(queue, maxCapacity, lock), "p1").start();
        new Thread(new Producer(queue, maxCapacity, lock), "p2").start();

        // 开启一个消费者线程消费
        new Thread(new Consumer(queue, lock), "c1").start();
        new Thread(new Consumer(queue, lock), "c2").start();
    }

    @AllArgsConstructor
    static class Producer implements Runnable {

        private Queue<Integer> queue;
        private int maxCapacity;
        private Lock lock;

        @Override
        public void run() {
            while (true) {
                try {
                    // 不停的生产数据
                    produce();
                    // 控制生产速率
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 生产方法
        private void produce() throws InterruptedException {

            lock.lock();
            try {
                String threadName = Thread.currentThread().getName();
                while (queue.size() == maxCapacity) {
                    // 队列满了,进行等待
                    log.info("生产者线程:{},发现队列容量已满，等待消费者消费", threadName);
                    producerFullCond.await();
                    log.info("生产者线程:{},已退出等待", threadName);
                }
                // 进行生产
                int element = new Random().nextInt(100);
                queue.add(element);
                log.info("生产者线程:{},已生产一个元素【{}】", threadName, element);
                // 唤醒等待的消费者线程
                consumerEmptyCond.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    @AllArgsConstructor
    static class Consumer implements Runnable {

        private Queue<Integer> queue;
        private Lock lock;

        @Override
        public void run() {

            while (true) {
                try {
                    consume();
                    // 控制消费速率
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {
            lock.lock();
            try {
                String threadName = Thread.currentThread().getName();
                while (queue.isEmpty()) {
                    log.info("消费者线程:{},发现队列数据为空，等待生产者生成", threadName);
                    consumerEmptyCond.await();
                    log.info("消费者线程:{},已退出等待", threadName);
                }
                int element = queue.remove();
                log.info("消费者线程:{},已消费一个元素【{}】", threadName, element);
                // 唤醒等待的生产者线程
                producerFullCond.signalAll();
            } catch (Exception e) {
                lock.unlock();
            }
        }
    }
}
