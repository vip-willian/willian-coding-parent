package cn.willian.coding.designmode.behavior.observer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:52:21
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Product {

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private Double price;
}
