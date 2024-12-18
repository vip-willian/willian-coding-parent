package cn.willian.coding.designmode.behavior.chain;

/**
 * 主任审批
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:58:28
 */
public class DirectorAuditor extends AbstractAuditor {
    public DirectorAuditor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            // 处理请求
            System.out.println("主任【" + this.name + "】审批采购单：" + request.getNumber() + "，金额：" + request.getAmount()
                + "元，采购目的：" + request.getReason());
        } else {
            // 转发请求
            if (nextAuditor != null) {
                this.nextAuditor.handleRequest(request);
            }
        }
    }
}
