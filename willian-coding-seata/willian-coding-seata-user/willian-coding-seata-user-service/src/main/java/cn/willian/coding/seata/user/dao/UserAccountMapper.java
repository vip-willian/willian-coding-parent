package cn.willian.coding.seata.user.dao;

import cn.willian.coding.seata.user.domain.UserAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:05
 */
@Mapper
public interface UserAccountMapper extends io.mybatis.mapper.Mapper<UserAccount, Long> {

}
