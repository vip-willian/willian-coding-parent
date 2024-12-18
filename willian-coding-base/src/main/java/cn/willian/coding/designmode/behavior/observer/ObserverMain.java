package cn.willian.coding.designmode.behavior.observer;

import cn.willian.coding.designmode.behavior.observer.bean.Product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 23:20:58
 */
public class ObserverMain {
    public static void main(String[] args) {

        ProductManageService productManageService = new ProductManageServiceImpl();
        // 注册观察者
        productManageService.addObserver(new UserAProductObserver());
        productManageService.addObserver(new ProductObserver() {
            @Override
            public void onChangeProductNew(Product product) {
                System.out.println("用户B接收到商品上新的消息通知：新商品-" + product);
            }

            @Override
            public void onChangeProductPrice(Product product, Double oldPrice, Double newPrice) {
                System.out.println(
                    "用户B接收到商品价格变更的消息通知：商品【 " + product.getName() + "】的价格从 【" + oldPrice + "】变更为 【" + newPrice + "】");
            }
        });

        // 添加新商品
        productManageService.addNewProduct("苹果电脑", 8000d);
        productManageService.addNewProduct("华为手机", 3600d);

        // 商品价格变更
        productManageService.setProductPrice("苹果电脑", 6666d);
    }
}
