package cn.willian.coding.designmode.structure.facade.order;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:48:29
 */
// 子系统2：订单管理
public class OrderManager {

    public void createOrder() {
        System.out.println("创建订单");
    }

    public void cancelOrder() {
        System.out.println("取消订单");
    }
}
