package palvvz.mapper;

import palvvz.domain.Task;
import palvvz.domain.User;
import palvvz.dto.task.CreateTaskRequest;
import palvvz.dto.task.TaskDto;
import palvvz.dto.task.UpdateTaskRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "userId", source = "user.id")
    TaskDto toDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modifiedAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    Task fromCreate(CreateTaskRequest req, User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "modifiedAt", expression = "java(java.time.Instant.now())")
    void update(@MappingTarget Task task, UpdateTaskRequest req, User user);
}
