package cn.willian.coding.redis.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cn.willian.coding.redis.domain.User;
import cn.willian.coding.redis.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 09:00:41
 */
@Mapper
public interface DataConverter {

    DataConverter MAPPER = Mappers.getMapper(DataConverter.class);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "phone", source = "phone")
    UserDTO convert(User user);
}
