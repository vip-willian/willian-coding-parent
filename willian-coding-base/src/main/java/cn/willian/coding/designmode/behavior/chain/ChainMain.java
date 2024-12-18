package cn.willian.coding.designmode.behavior.chain;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 16:13:51
 */
public class ChainMain {
    public static void main(String[] args) {

        Auditor chain = getAuditorChain();
        // 创建采购单
        PurchaseRequest pr1 = new PurchaseRequest(10001, 45000d, "购买倚天剑");
        chain.handleRequest(pr1);

        PurchaseRequest pr2 = new PurchaseRequest(10002, 60000d, "购买《葵花宝典》");
        chain.handleRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest(10003, 160000d, "购买《金刚经》");
        chain.handleRequest(pr3);
    }

    public static Auditor getAuditorChain() {

        AbstractAuditor director, manager, president;
        director = new DirectorAuditor("张无忌");
        manager = new ManagerAuditor("郭靖");
        president = new PresidentAuditor("张三丰");

        // 创建职责链
        director.setNextAuditor(manager);
        manager.setNextAuditor(president);
        return director;
    }
}
