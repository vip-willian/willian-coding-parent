package cn.willian.coding.designmode.structure.adapter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:07:16
 */
// Adaptee（适配者类）
public class StandardAdapteePay {

    public void pay(double money) {
        System.out.println("标准支付接口，支付金额: " + money);
    }
}
