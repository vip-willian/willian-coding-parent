package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:18:32
 */
public interface AccountState {

    void deposit(double amount);

    void withdraw(double amount);

    void computeInterest();

    void stateCheck();
}
