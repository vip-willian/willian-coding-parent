package cn.willian.coding.designmode.behavior.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.willian.coding.designmode.behavior.observer.bean.Product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 23:10:53
 */
public class ProductManageServiceImpl implements ProductManageService {

    private final List<ProductObserver> observers;
    private final Map<String, Product> productMap;

    public ProductManageServiceImpl() {
        observers = new ArrayList<>();
        productMap = new HashMap<>();
    }

    @Override
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void addNewProduct(String name, Double price) {
        Product p = Product.of(name, price);
        productMap.put(p.getName(), p);
        // 发送商品上新通知
        observers.forEach(o -> o.onChangeProductNew(p));
    }

    @Override
    public void setProductPrice(String name, Double price) {

        Product product = productMap.get(name);
        if (product != null) {
            Double oldPrice = product.getPrice();
            product.setPrice(price);
            // 发送商品价格更新通知
            observers.forEach(o -> o.onChangeProductPrice(product, oldPrice, product.getPrice()));
        }
    }
}
