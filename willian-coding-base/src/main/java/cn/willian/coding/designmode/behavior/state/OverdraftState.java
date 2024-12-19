package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:32:25
 */
// 透支状态：具体状态类
public class OverdraftState extends AbstractAccountState {

    public OverdraftState(BankAccount account) {
        super(account);
    }

    public OverdraftState(AbstractAccountState state) {
        super(state.account);
    }

    public void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        stateCheck();
    }

    public void withdraw(double amount) {
        account.setBalance(account.getBalance() - amount);
        stateCheck();
    }

    public void computeInterest() {
        System.out.println("计算利息！");
    }

    // 状态转换
    public void stateCheck() {
        // 透支状态下，当金额重新大于0,恢复可用
        if (account.getBalance() > 0) {
            account.setState(new NormalState(this));
        }
        // 透支状态下，当金额等于2000,转为受限状态
        else if (account.getBalance() == -2000) {
            account.setState(new RestrictedState(this));
        } else if (account.getBalance() < -2000) {
            System.out.println("操作受限！");
        }
    }
}
