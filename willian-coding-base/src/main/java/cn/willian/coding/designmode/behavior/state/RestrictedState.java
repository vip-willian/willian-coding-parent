package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:32:34
 */
// 受限状态：具体状态类
public class RestrictedState extends AbstractAccountState {
    public RestrictedState(BankAccount account) {
        super(account);
    }

    public RestrictedState(AbstractAccountState state) {
        super(state.account);
    }

    public void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        stateCheck();
    }

    public void withdraw(double amount) {
        System.out.println("帐号受限，取款失败");
    }

    public void computeInterest() {
        System.out.println("计算利息！");
    }

    // 状态转换
    public void stateCheck() {

        if (account.getBalance() > 0) {
            account.setState(new NormalState(this));
        } else if (account.getBalance() > -2000) {
            account.setState(new OverdraftState(this));
        }
    }
}
