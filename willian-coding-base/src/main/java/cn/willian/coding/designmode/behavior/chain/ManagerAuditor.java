package cn.willian.coding.designmode.behavior.chain;

/**
 * 总经理审批
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:58:28
 */
public class ManagerAuditor extends AbstractAuditor {

    public ManagerAuditor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        // 处理请求
        if (request.getAmount() < 100000) {
            System.out.println("总经理【" + this.name + "】审批采购单：" + request.getNumber() + "，金额：" + request.getAmount()
                + "元，采购目的：" + request.getReason());
        } else {
            // 转发请求
            this.nextAuditor.handleRequest(request);
        }
    }
}
