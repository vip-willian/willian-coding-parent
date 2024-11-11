package cn.willian.coding.seata.user.facade;

import cn.willian.coding.seata.goods.UserServiceFacade;
import cn.willian.coding.seata.user.service.UserAccountService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 14:12:41
 */
@DubboService
public class GoodsServiceFacadeImpl implements UserServiceFacade {

    @Resource
    private UserAccountService userAccountService;

    @Override
    public void deductUserAmount(Long userId, BigDecimal orderAmount) {

        userAccountService.deductUserAmount(userId, orderAmount);
    }
}
