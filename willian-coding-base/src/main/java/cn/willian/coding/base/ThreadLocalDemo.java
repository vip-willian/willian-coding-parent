package cn.willian.coding.base;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 22:47:50
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->10);

    public void incr(){
        threadLocal.set(threadLocal.get()+10);
        System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());

    }

    public static void main(String[] args) {

        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();


        for (int i = 0; i < 5; i++) {
            new Thread(threadLocalDemo::incr).start();
        }


//        ThreadLocal<User>   = new ThreadLocal<>();
//        ThreadLocal<User> userThreadLocal2 = new ThreadLocal<>();
//        userThreadLocal1.set(new User());
//        userThreadLocal2.set(new User());

        // userThreadLocal 变量 强引用了 ThreadLocal 对象
        // 当前线程 强引用 ThreadLocalMap对象
        // ThreadLocalMap[i] 强引用了 Entry
        // Entry.key 若引用了 ThreadLocal对象
        // Entry.value 强引用了 User对象
    }

    static class User{

        RateLimiter rateLimiter = RateLimiter.create(1);
    }
}

// 限流算法
// 计数器限流，临界值问题
// 滑动窗口限流
// 漏桶算法
// 令牌桶算法

