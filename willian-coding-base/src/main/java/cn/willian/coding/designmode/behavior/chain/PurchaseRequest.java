package cn.willian.coding.designmode.behavior.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 采购单请求
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:50:16
 */
@Data
@AllArgsConstructor
public class PurchaseRequest {

    /**
     * 采购单编号
     */
    private Integer number;
    /**
     * 采购单金额
     */
    private Double amount;
    /**
     * 采购原因
     */
    private String reason;
}
