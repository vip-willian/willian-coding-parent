package cn.willian.coding.seata.user.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:37:15
 */
@LocalTCC
public interface UserAccountTccService {

    /**
     * 扣减用户余额
     */
    @TwoPhaseBusinessAction(name = "deductUserAmount", commitMethod = "confirm", rollbackMethod = "cancel")
    void deductUserAmount(@BusinessActionContextParameter(value = "userId") Long userId, @BusinessActionContextParameter(value = "orderAmount") BigDecimal orderAmount);

    boolean confirm(BusinessActionContext context);

    boolean cancel(BusinessActionContext context);
}
