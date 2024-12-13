package cn.willian.coding.designmode.created.singlegon;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 10:01:28
 */
@SuppressWarnings("all")
public class LazySingleton {

    private static volatile LazySingleton instance;

    private LazySingleton() {}

    // 双重检查
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
