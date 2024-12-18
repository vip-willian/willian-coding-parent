package cn.willian.coding.designmode.behavior.chain;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 16:25:20
 */
public interface Auditor {

    /**
     * 处理采购请求
     *
     * @param request 请求类
     */
    void handleRequest(PurchaseRequest request);
}
