package cn.willian.coding.designmode.behavior.strategy;

import lombok.Setter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:47:26
 */
// 电影票类：环境类
@Setter
public class MovieTicket {
    private double price;
    // 维持一个对抽象折扣类的引用
    private Discount discount;

    public double getPrice() {
        // 调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
