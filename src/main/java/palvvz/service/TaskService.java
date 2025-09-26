package palvvz.service;

import palvvz.dto.task.CreateTaskRequest;
import palvvz.dto.task.TaskDto;
import palvvz.dto.task.UpdateTaskRequest;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDto> getAll();
    TaskDto getById(Long id);
    TaskDto create(CreateTaskRequest request);
    TaskDto update(Long id, UpdateTaskRequest request);
    TaskDto patch(Long id, Map<String, Object> fields);
    void delete(Long id);
    List<TaskDto> getByUserId(Long userId);
}
