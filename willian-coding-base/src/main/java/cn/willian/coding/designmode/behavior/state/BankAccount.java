package cn.willian.coding.designmode.behavior.state;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:17:46
 */
// 银行账户: 环境类
@Data
public class BankAccount {

    private AccountState state;
    private String owner;
    private double balance;

    public BankAccount(String owner) {
        this(0d, owner);
    }

    public BankAccount(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
        this.state = new NormalState(this);
    }

    /**
     * 存款操作
     *
     * @param amount 存款金额
     */
    public void deposit(double amount) {
        System.out.println(this.owner + "存款" + amount);
        state.deposit(amount); // 调用状态对象的deposit()方法
        System.out.println("现在余额为" + this.balance);
        System.out.println("现在帐户状态为" + this.state.getClass().getName());
        System.out.println("---------------------------------------------");
    }

    /**
     * 取款操作
     *
     * @param amount 取款金额
     */
    public void withdraw(double amount) {
        System.out.println(this.owner + "取款" + amount);
        state.withdraw(amount); // 调用状态对象的withdraw()方法
        System.out.println("现在余额为" + this.balance);
        System.out.println("现在帐户状态为" + this.state.getClass().getName());
        System.out.println("---------------------------------------------");
    }

    /**
     * 计算利息
     */
    public void computeInterest() {
        state.computeInterest(); // 调用状态对象的computeInterest()方法
    }
}
