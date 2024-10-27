package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-04 10:32:51
 */
@Slf4j
public class VolatileSingleObjectDemo {

    public static void main(String[] args) {

        // 开10个线程，获取对象实例
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                SingleObject instance = SingleObject.getInstance();
                log.info("当前线程{}获取的对象SingleObject实例:{}", threadName, instance);

                SingleObject2 instance2 = SingleObject2.getInstance();
                log.info("当前线程{}获取的对象SingleObject2实例:{}", threadName, instance2);
            }, String.valueOf(i)).start();
        }
    }

    // volatile 保障了可见性和有序性
    // 通过禁止指定重排序（插入特定内存屏障）
    // 通过happens-before规则（volatile字段写 对 后续 volatile字段读 可见）
    static class SingleObject {

        private static volatile SingleObject instance;

        private SingleObject() {

        }

        public static SingleObject getInstance() {
            // 1、第一次检查
            if (instance == null) {
                synchronized (SingleObject.class) {
                    // 2、第二次检查
                    if (instance == null) {
                        // 分三个步骤创建对象
                        // 1）分配内存空间
                        // 2）在内存空间中初始化SingleObject对象
                        // 3）将内存地址赋值给instance对象
                        instance = new SingleObject();
                    }
                }
            }
            return instance;
        }
    }

    static class SingleObject2 {

        private static SingleObject2 instance;

        private SingleObject2() {

        }

        public static SingleObject2 getInstance() {
            if (instance == null) {
                synchronized (SingleObject2.class) {
                    if (instance == null) {
                        instance = new SingleObject2();
                    }
                }
            }
            return instance;
        }
    }
}
