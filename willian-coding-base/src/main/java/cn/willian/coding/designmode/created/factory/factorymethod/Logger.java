package cn.willian.coding.designmode.created.factory.factorymethod;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:35:32
 */
public interface Logger {

    /**
     * debug级别日志
     */
    void debug(String message);

    /**
     * info级别日志
     */
    void info(String message);

    /**
     * warn级别日志
     */
    void warn(String message);

    /**
     * error级别日志
     */
    void error(String message);
}
