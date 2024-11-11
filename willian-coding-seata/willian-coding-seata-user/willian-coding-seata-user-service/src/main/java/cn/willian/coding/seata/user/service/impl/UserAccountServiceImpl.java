package cn.willian.coding.seata.user.service.impl;

import cn.willian.coding.seata.user.dao.UserAccountMapper;
import cn.willian.coding.seata.user.domain.UserAccount;
import cn.willian.coding.seata.user.manage.UserAccountManager;
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
    private UserAccountManager userAccountManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductUserAmount(Long userId, BigDecimal orderAmount) {

        userAccountManager.deductUserAmount(userId, orderAmount);
    }
}
