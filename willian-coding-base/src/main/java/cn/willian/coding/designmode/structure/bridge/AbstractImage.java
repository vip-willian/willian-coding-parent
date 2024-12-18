package cn.willian.coding.designmode.structure.bridge;

import lombok.Setter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:03:14
 */
@Setter
public abstract class AbstractImage {

    protected  SystemImage systemImage;

    public abstract void parseFile(String fileName);
}
