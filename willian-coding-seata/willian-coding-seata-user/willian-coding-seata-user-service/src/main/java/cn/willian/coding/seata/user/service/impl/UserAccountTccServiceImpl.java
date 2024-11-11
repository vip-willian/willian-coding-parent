package cn.willian.coding.seata.user.service.impl;

import cn.willian.coding.seata.user.dao.UserAccountTccMapper;
import cn.willian.coding.seata.user.domain.UserAccountTCC;
import cn.willian.coding.seata.user.enums.TransactionState;
import cn.willian.coding.seata.user.manage.UserAccountManager;
import cn.willian.coding.seata.user.service.UserAccountTccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 23:49:52
 */
@Service
public class UserAccountTccServiceImpl implements UserAccountTccService {

    @Resource
    private UserAccountManager userAccountManager;
    @Resource
    private UserAccountTccMapper userAccountTccMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductUserAmount(Long userId, BigDecimal orderAmount) {

        // 考虑业务悬挂
        String xid = RootContext.getXID();
        Optional<UserAccountTCC> userAccountTccOpt = userAccountTccMapper.selectByPrimaryKey(xid);
        if (userAccountTccOpt.isPresent()) {
            // 已经执行过了，有可能已经被取消了
            return;
        }
        // 扣减金额
        userAccountManager.deductUserAmount(userId, orderAmount);
        // 记录冻结金额
        insertTcc(xid, userId, orderAmount, TransactionState.TRY);
    }

    @Override
    public boolean confirm(BusinessActionContext context) {

        // 也需要考虑幂等性
        // 事务执行成功， 删除冻结记录
        String xid = context.getXid();
        int count = userAccountTccMapper.deleteByPrimaryKey(xid);
        return count == 1;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {

        // 事务执行失败
        Optional<UserAccountTCC> userAccountTccOpt = userAccountTccMapper.selectByPrimaryKey(context.getXid());
        if (!userAccountTccOpt.isPresent()) {
            // 空回滚处理
            Long userId = context.getActionContext("userId", Long.class);
            insertTcc(context.getXid(), userId, BigDecimal.ZERO, TransactionState.CANCEL);
            return true;
        }
        UserAccountTCC userAccountTcc = userAccountTccOpt.get();
        if (TransactionState.CANCEL.getCode().equals(userAccountTcc.getState())) {
            // 业务幂等处理： 已经执行过取消，无需重复取消
            return true;
        }
        // 恢复金额
        userAccountManager.refundUserAmount(userAccountTcc.getUserId(), userAccountTcc.getFreezeAmount());
        // 冻结金额清零，事务状态更新为已取消
        userAccountTcc.setFreezeAmount(BigDecimal.ZERO);
        userAccountTcc.setState(TransactionState.CANCEL.getCode());
        int count = userAccountTccMapper.updateByPrimaryKeySelective(userAccountTcc);
        return count == 1;
    }

    private void insertTcc(String xid, Long userId, BigDecimal freezeAmount, TransactionState state) {

        UserAccountTCC userAccountTCC = new UserAccountTCC();
        userAccountTCC.setXid(xid);
        userAccountTCC.setUserId(userId);
        userAccountTCC.setFreezeAmount(freezeAmount);
        userAccountTCC.setState(state.getCode());
        userAccountTccMapper.insert(userAccountTCC);
    }
}
