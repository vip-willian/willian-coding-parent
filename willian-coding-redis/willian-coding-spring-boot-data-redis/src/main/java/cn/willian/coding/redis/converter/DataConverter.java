package cn.willian.coding.redis.converter;

import org.mapstruct.Mapper;

import cn.willian.coding.redis.domain.User;
import cn.willian.coding.redis.dto.UserDTO;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 09:00:41
 */
@Mapper
public interface DataConverter {

    DataConverter MAPPER = Mappers.getMapper(DataConverter.class);

    UserDTO convert(User user);
}
