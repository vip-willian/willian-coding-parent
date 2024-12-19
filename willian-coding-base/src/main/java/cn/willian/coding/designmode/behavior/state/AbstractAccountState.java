package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:25:20
 */
public abstract class AbstractAccountState implements AccountState{

    protected BankAccount account;

    public AbstractAccountState(BankAccount account) {
        this.account = account;
    }
}
