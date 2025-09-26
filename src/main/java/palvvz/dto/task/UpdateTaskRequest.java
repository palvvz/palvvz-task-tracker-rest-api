package palvvz.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateTaskRequest {
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private Boolean isDone;
    @NotNull
    private Long userId;
}
