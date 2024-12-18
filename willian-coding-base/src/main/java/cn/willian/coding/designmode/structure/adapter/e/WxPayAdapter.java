package cn.willian.coding.designmode.structure.adapter.e;

import cn.willian.coding.designmode.structure.adapter.StandardAdapteePay;
import cn.willian.coding.designmode.structure.adapter.TargetPay;

/**
 * 类适配器模式
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:08:32
 */
public class WxPayAdapter extends StandardAdapteePay implements TargetPay {

    @Override
    public void pay(double money) {
        System.out.println("微信支付升级适配,支付优惠1元");
        super.pay(money - 1);
    }
}
