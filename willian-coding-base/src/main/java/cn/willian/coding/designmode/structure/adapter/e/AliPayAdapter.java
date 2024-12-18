package cn.willian.coding.designmode.structure.adapter.e;

import cn.willian.coding.designmode.structure.adapter.StandardAdapteePay;
import cn.willian.coding.designmode.structure.adapter.TargetPay;

/**
 * 类适配器模式
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:08:32
 */
public class AliPayAdapter extends StandardAdapteePay implements TargetPay {

    @Override
    public void pay(double money) {
        System.out.println("支付宝支付升级适配,支付优惠2元");
        super.pay(money - 2);
    }
}
