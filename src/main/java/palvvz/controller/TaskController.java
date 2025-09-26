package palvvz.controller;

import palvvz.dto.task.CreateTaskRequest;
import palvvz.dto.task.TaskDto;
import palvvz.dto.task.UpdateTaskRequest;
import palvvz.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${app.main.endpoint}/tasks")
@RequiredArgsConstructor
public class TaskController {



    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAll(@RequestParam(value = "userId", required = false) Long userId) {
        if (userId != null) {
            return taskService.getByUserId(userId);
        }
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateTaskRequest request) {
        TaskDto dto = taskService.create(request);
        return ResponseEntity.created(URI.create("/api/tasks/" + dto.getId())).body(dto);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable("id") Long id, @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    @PatchMapping("/{id}")
    public TaskDto patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return taskService.patch(id, fields);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
