package cn.willian.coding.designmode.behavior.observer;

import cn.willian.coding.designmode.behavior.observer.bean.Product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 23:06:55
 */
public interface ProductObserver {

    /**
     * 产品上新变更通知
     */
    void onChangeProductNew(Product product);

    /**
     * 产品价格变更通知
     */
    void onChangeProductPrice(Product product, Double oldPrice, Double newPrice);
}
