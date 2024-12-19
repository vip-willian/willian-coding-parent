package cn.willian.coding.designmode.behavior.strategy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:48:24
 */
// 会员票折扣类：具体策略类
public class VipDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("会员票：");
        System.out.println("增加积分！");
        return price * 0.5;
    }
}
