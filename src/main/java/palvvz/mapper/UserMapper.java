package palvvz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import palvvz.domain.User;
import palvvz.dto.user.UpdateUserDto;
import palvvz.dto.user.UserCreateEditDto;
import palvvz.dto.user.UserRequestRegisterDto;
import palvvz.dto.user.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserRequestRegisterDto request);

    User fromCreate(UserCreateEditDto req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User user, UpdateUserDto req);
}
