package cn.willian.coding.designmode.created.singlegon;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 10:04:52
 */
@SuppressWarnings("all")
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {

    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
}
