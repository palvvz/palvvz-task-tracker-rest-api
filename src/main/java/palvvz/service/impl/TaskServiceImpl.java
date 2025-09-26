package palvvz.service.impl;

import palvvz.domain.Task;
import palvvz.domain.User;
import palvvz.dto.task.CreateTaskRequest;
import palvvz.dto.task.TaskDto;
import palvvz.dto.task.UpdateTaskRequest;
import palvvz.exception.ResourceNotFoundException;
import palvvz.mapper.TaskMapper;
import palvvz.repository.TaskRepository;
import palvvz.repository.UserRepository;
import palvvz.service.TaskService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto create(CreateTaskRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));
        Task task = taskMapper.fromCreate(request, user);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto update(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));
        taskMapper.update(task, request, user);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto patch(Long id, Map<String, Object> fields) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));

        UpdateTaskRequest req = new UpdateTaskRequest(
                task.getTitle(),
                task.getDescription(),
                task.getIsDone(),
                task.getUser().getId()
        );
        try {
            objectMapper.updateValue(req, fields);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + req.getUserId()));

        taskMapper.update(task, req, user);
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        return taskRepository.findByUser(user).stream().map(taskMapper::toDto).toList();
    }
}
