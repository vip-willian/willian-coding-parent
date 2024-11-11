package cn.willian.coding.seata.user.service.impl;

import cn.willian.coding.seata.user.dao.UserAccountMapper;
import cn.willian.coding.seata.user.domain.UserAccount;
import cn.willian.coding.seata.user.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:42:25
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
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
}
