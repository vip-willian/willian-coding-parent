package cn.willian.coding.designmode.structure.facade.shoppingcar;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:49:04
 */
// 子系统3：购物车管理
public class ShoppingCarManager {

    public void addToCart(String productName) {
        System.out.println("添加商品到购物车：" + productName);
    }

    public void removeFromCart(String productName) {
        System.out.println("从购物车移除商品：" + productName);
    }
}
