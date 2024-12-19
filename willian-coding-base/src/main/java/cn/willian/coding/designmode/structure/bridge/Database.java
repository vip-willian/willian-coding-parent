package cn.willian.coding.designmode.structure.bridge;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 17:31:19
 */
public interface Database {

    /**
     * 从不同的数据源读取
     * 
     * @return 数据内容
     */
    String read();

    /**
     * 输出最终结果
     * 
     * @return 最终结果
     */
    String input();

    /**
     * 添加转换器
     * 
     * @param converter 转换器
     */
    void setConverter(Converter converter);
}
