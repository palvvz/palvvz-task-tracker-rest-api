package palvvz.mapper;

import palvvz.domain.User;
import palvvz.dto.user.UserCreateEditDto;
import palvvz.dto.user.UpdateUserDto;
import palvvz.dto.user.UserReadDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toDto(User user);

    User fromCreate(UserCreateEditDto req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User user, UpdateUserDto req);
}
