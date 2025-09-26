package palvvz.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateTaskRequest {
    @NotBlank
    private String title;
    private String description;
    private Boolean isDone = false;
    @NotNull
    private Long userId;
}
