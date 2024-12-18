package cn.willian.coding.designmode.structure.facade;

import cn.willian.coding.designmode.structure.facade.account.AccountManager;
import cn.willian.coding.designmode.structure.facade.order.OrderManager;
import cn.willian.coding.designmode.structure.facade.product.ProductManager;
import cn.willian.coding.designmode.structure.facade.shoppingcar.ShoppingCarManager;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:53:22
 */
public class ShoppingFacade {

    private final AccountManager accountManager;
    private final OrderManager orderManager;
    private final ShoppingCarManager shoppingCarManager;
    private final ProductManager productManager;

    public ShoppingFacade() {
        this.accountManager = new AccountManager();
        this.orderManager = new OrderManager();
        this.shoppingCarManager = new ShoppingCarManager();
        this.productManager = new ProductManager();
    }

    /**
     * 购买商品
     *
     * @param productName 商品
     */
    public void buy(String productName, double price) {
        // 添加商品到购物车
        shoppingCarManager.addToCart(productName);
        // 扣减商品库存
        productManager.addProduct(productName);
        // 创建订单
        orderManager.createOrder();
        // 扣减账户
        accountManager.pay(price);
        System.out.println("购买成功！");
    }

    /**
     * 取消购买商品
     *
     * @param productName 商品
     */
    public void cancel(String productName, double price) {

        // 从购物车移除
        shoppingCarManager.removeFromCart(productName);
        // 回退商务库存
        productManager.removeProduct(productName);
        // 取消订单
        orderManager.cancelOrder();
        // 退款
        accountManager.refund(price);
        System.out.println("取消购买成功！");
    }

}
