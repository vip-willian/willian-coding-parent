package cn.willian.coding.designmode.structure.adapter.o;

import cn.willian.coding.designmode.structure.adapter.StandardAdapteePay;
import cn.willian.coding.designmode.structure.adapter.TargetPay;

/**
 * 对象适配器模式
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:08:32
 */
public class AliPayAdapter implements TargetPay {

    // 基于标准的支付接口,升级适配
    StandardAdapteePay adapteePay;

    public AliPayAdapter(StandardAdapteePay adapteePay) {
        this.adapteePay = adapteePay;
    }

    @Override
    public void pay(double money) {
        System.out.println("支付宝支付升级适配,支付优惠2元");
        adapteePay.pay(money - 2);
    }
}
