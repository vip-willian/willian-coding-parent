package cn.willian.coding.designmode.behavior.state;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:40:47
 */
public class StateMain {

    public static void main(String[] args) {
        BankAccount acc = new BankAccount(0d, "Willian");
        acc.deposit(1000);
        acc.withdraw(2000);
        acc.deposit(3000);
        acc.withdraw(4000);
        acc.withdraw(1000);
        acc.computeInterest();
    }
}
