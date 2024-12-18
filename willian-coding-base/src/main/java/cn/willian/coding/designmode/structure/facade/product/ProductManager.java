package cn.willian.coding.designmode.structure.facade.product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:48:48
 */
// 子系统1：商品管理
public class ProductManager {

    public void addProduct(String productName) {
        System.out.println("商品：" + productName + "的库存-1");
    }

    public void removeProduct(String productName) {
        System.out.println("商品：" + productName + "的库存+1");
    }
}
