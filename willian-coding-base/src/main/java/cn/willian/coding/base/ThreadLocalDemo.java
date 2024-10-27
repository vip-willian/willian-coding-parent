package cn.willian.coding.base;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 22:47:50
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
        userThreadLocal.set(new User());

        // userThreadLocal 变量 强引用了 ThreadLocal 对象
        // 当前线程 强引用 ThreadLocalMap对象
        // ThreadLocalMap[i] 强引用了 Entry
        // Entry.key 若引用了 ThreadLocal对象
        // Entry.value 强引用了 User对象


    }

    static class User{

    }
}

