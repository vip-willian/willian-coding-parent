package cn.willian.coding.designmode.structure.bridge;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 17:30:52
 */
// 转换器接口 Implementor
public interface Converter {

    /**
     * 数据内容转换
     * 
     * @param source 内容
     * @return 格式化结果
     */
    String convert(String source);
}
