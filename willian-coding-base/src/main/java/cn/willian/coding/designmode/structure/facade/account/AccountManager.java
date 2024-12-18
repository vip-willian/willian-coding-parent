package cn.willian.coding.designmode.structure.facade.account;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:48:29
 */
// 子系统4：账户管理
public class AccountManager {

    public void pay(double money) {
        System.out.println("支付 " + money + "元");
    }

    public void refund(double money) {
        System.out.println("退款 " + money + "元");
    }
}
