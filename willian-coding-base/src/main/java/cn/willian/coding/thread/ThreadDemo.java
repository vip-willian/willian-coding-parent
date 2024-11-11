package cn.willian.coding.thread;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-01 16:15:27
 */
public class ThreadDemo {

    public static void main(String[] args) {

        // -- 原子性：不可分割 i++
        // 加锁、volatile
        // -- 有序性
        // 多线程代码执行顺序会发生变更，单线程执行结果不变
        // -- 可见性

         // volatile 保障了 有序性和可见性
       // synchronized 保障了原子性、有序性、可见性
       // atomic 保障了 原子性
    }
}
