package cn.willian.coding.seata.goods;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 14:11:35
 */
public interface UserServiceFacade {

    /**
     * 扣减用户余额
     */
    void deductUserAmount(Long userId, BigDecimal orderAmount);
}
