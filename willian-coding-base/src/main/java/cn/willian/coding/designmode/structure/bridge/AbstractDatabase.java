package cn.willian.coding.designmode.structure.bridge;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 17:34:58
 */
// 抽象类(Abstraction)
@Data
public abstract class AbstractDatabase implements Database {

    protected Converter converter;

    @Override
    public String input() {

        String content = read();
        return converter.convert(content);
    }
}
