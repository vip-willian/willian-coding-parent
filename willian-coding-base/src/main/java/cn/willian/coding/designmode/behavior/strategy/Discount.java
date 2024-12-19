package cn.willian.coding.designmode.behavior.strategy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:47:49
 */
// 折扣类：抽象策略类
public interface Discount {
    double calculate(double price);
}
