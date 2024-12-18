package cn.willian.coding.designmode.behavior.iterator;

import com.google.common.collect.Lists;

import cn.willian.coding.designmode.behavior.iterator.bean.Order;
import cn.willian.coding.designmode.behavior.iterator.bean.Product;
import cn.willian.coding.designmode.behavior.iterator.impl.OrderList;
import cn.willian.coding.designmode.behavior.iterator.impl.ProductList;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:57:32
 */
public class IteratorMain {

    public static void main(String[] args) {
        OrderList orderList =
            new OrderList(Lists.newArrayList(Order.of(1001L, 300d), Order.of(1002L, 4658.9d), Order.of(1003L, 500d)));
        System.out.println("------订单列表正向遍历------");
        Iterator<Order> orderIterator = orderList.iterator();
        while (!orderIterator.isLast()) {
            Order order = orderIterator.getNext();
            System.out.println(order);
            // 移动到下一个
            orderIterator.next();
        }
        System.out.println("------订单列表逆向遍历------");
        while (!orderIterator.isFirst()) {
            Order order = orderIterator.getPrevious();
            System.out.println(order);
            // 移动到上一个
            orderIterator.previous();
        }
        ProductList productList = new ProductList(Lists.newArrayList(Product.of("葵花宝典", 100d),
            Product.of("降龙18掌", 998d), Product.of("苹果笔记本", 20000d), Product.of("如来神掌", 30d)));
        System.out.println("------产品列表正向遍历------");
        Iterator<Product> productIterator = productList.iterator();
        while (!productIterator.isLast()) {
            Product product = productIterator.getNext();
            System.out.println(product);
            // 移动到下一个
            productIterator.next();
        }
        System.out.println("------产品列表逆向遍历------");
        while (!productIterator.isFirst()) {
            Product product = productIterator.getPrevious();
            System.out.println(product);
            // 移动到上一个
            productIterator.previous();
        }
    }
}
