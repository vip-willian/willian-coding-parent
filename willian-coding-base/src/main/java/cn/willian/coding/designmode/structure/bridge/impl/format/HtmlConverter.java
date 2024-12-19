package cn.willian.coding.designmode.structure.bridge.impl.format;

import cn.willian.coding.designmode.structure.bridge.Converter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 17:38:49
 */
// ConcreteImplementor 具体实现类
public class HtmlConverter implements Converter {

    @Override
    public String convert(String source) {

        System.out.println("转换成HTML格式");
        return "<h1>" + source + "</h1>";
    }
}
