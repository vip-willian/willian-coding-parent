package cn.willian.coding.seata.order.remote.dubbo;

import cn.willian.coding.seata.goods.UserServiceFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 13:59:57
 */
@Service
public class UserDubboRemoteService {


    @DubboReference
    private UserServiceFacade userServiceFacade;


    /**
     * 扣减用户余额
     */
    public void deductUserAmount(Long userId, BigDecimal orderAmount) {

        userServiceFacade.deductUserAmount(userId, orderAmount);
    }
}
