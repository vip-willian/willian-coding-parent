package cn.willian.coding.designmode.behavior.observer;

import cn.willian.coding.designmode.behavior.observer.bean.Product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 23:24:49
 */
public class UserAProductObserver implements ProductObserver {

    @Override
    public void onChangeProductNew(Product product) {
        System.out.println("用户A接收到商品上新的消息通知：新商品-" + product);
    }

    @Override
    public void onChangeProductPrice(Product product, Double oldPrice, Double newPrice) {
        System.out.println("用户A接收到商品价格变更的消息通知：商品【 " + product.getName() + "】的价格从 【" + oldPrice + "】变更为 【" + newPrice + "】");
    }
}
