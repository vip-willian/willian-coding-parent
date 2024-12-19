package cn.willian.coding.designmode.structure.adapter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:05:11
 */
// Target（目标抽象类)
public interface TargetPay {

    /**
     * 支付接口
     * @param money 支付金额
     */
    void pay(double money);
}
