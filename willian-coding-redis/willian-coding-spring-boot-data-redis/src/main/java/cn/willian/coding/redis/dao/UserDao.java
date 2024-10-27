package cn.willian.coding.redis.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.willian.coding.redis.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 08:01:10
 */
@Mapper
public interface UserDao {

    int insert(User user);

    User getByPhone(@Param("phone") String phone);
}
