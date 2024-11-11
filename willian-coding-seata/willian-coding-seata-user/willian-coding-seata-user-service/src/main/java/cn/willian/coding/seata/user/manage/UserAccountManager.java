package cn.willian.coding.seata.user.manage;

import cn.willian.coding.seata.user.dao.UserAccountMapper;
import cn.willian.coding.seata.user.domain.UserAccount;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 23:58:06
 */
@Repository
public class UserAccountManager {

    @Resource
    private UserAccountMapper userAccountMapper;

    public void deductUserAmount(Long userId, BigDecimal orderAmount) {

        Optional<UserAccount> userAccountOpt = userAccountMapper.selectByPrimaryKey(userId);
        if (!userAccountOpt.isPresent()) {
            throw new RuntimeException("用户账号不存在");
        }
        UserAccount userAccount = userAccountOpt.get();
        if (userAccount.getAmount().compareTo(orderAmount) < 0) {
            throw new RuntimeException("用户余额不足，下单失败");
        }
        userAccount.setAmount(userAccount.getAmount().subtract(orderAmount));
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }

    public void refundUserAmount(Long userId, BigDecimal orderAmount) {

        Optional<UserAccount> userAccountOpt = userAccountMapper.selectByPrimaryKey(userId);
        if (!userAccountOpt.isPresent()) {
            throw new RuntimeException("用户账号不存在");
        }
        UserAccount userAccount = userAccountOpt.get();
        userAccount.setAmount(userAccount.getAmount().add(orderAmount));
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }
}
