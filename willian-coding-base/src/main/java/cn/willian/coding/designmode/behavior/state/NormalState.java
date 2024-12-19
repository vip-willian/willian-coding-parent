package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:18:32
 */
// 正常状态：具体状态类
public class NormalState extends AbstractAccountState {

    public NormalState(BankAccount account) {
        super(account);
    }

    public NormalState(AbstractAccountState state) {
        super(state.account);
    }

    // 账号正常状态下存钱
    @Override
    public void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        stateCheck();
    }

    // 账号正常状态下取钱
    @Override
    public void withdraw(double amount) {
        account.setBalance(account.getBalance() - amount);
        stateCheck();
    }

    @Override
    public void computeInterest() {
        System.out.println("正常状态，无须支付利息！");
    }

    @Override
    public void stateCheck() {
        if (account.getBalance() > -2000 && account.getBalance() <= 0) {
            account.setState(new OverdraftState(this));
        } else if (account.getBalance() == -2000) {
            account.setState(new RestrictedState(this));
        } else if (account.getBalance() < -2000) {
            System.out.println("操作受限！");
        }
    }
}
