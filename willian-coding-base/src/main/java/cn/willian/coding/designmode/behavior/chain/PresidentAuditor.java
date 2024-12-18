package cn.willian.coding.designmode.behavior.chain;

/**
 * 董事长审批
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:58:28
 */
public class PresidentAuditor extends AbstractAuditor {

    public PresidentAuditor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        // 处理请求
        System.out.println("董事长【" + this.name + "】审批采购单：" + request.getNumber() + "，金额：" + request.getAmount()
            + "元，采购目的：" + request.getReason());
    }
}
