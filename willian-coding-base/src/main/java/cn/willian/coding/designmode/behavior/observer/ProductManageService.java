package cn.willian.coding.designmode.behavior.observer;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 23:10:53
 */
public interface ProductManageService {

    /**
     * 添加新商品
     */
    void addNewProduct(String name, Double price);

    /**
     * 商品价格变更
     */
    void setProductPrice(String name, Double price);

    /**
     * 添加观察者
     */
    void addObserver(ProductObserver observer);

    /**
     * 移除观察者
     */
    void removeObserver(ProductObserver observer);
}
