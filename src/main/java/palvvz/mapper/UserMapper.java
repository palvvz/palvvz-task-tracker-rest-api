package palvvz.mapper;

import org.mapstruct.Mapper;
import palvvz.domain.User;
import palvvz.dto.user.UserRequestDto;
import palvvz.dto.user.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto request);
}
