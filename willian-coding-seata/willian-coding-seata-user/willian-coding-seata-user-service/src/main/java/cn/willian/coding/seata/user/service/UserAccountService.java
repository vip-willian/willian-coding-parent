package cn.willian.coding.seata.user.service;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:37:15
 */
public interface UserAccountService {

    /**
     * 扣减用户余额
     */
    void deductUserAmount(Long userId, BigDecimal orderAmount);
}
