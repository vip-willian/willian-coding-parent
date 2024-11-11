package cn.willian.coding.seata.user.dao;

import cn.willian.coding.seata.user.domain.UserAccount;
import cn.willian.coding.seata.user.domain.UserAccountTCC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:05
 */
@Mapper
public interface UserAccountTccMapper extends io.mybatis.mapper.Mapper<UserAccountTCC, String> {

}
