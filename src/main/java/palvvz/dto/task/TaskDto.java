package palvvz.dto.task;

import lombok.*;
import java.time.Instant;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Instant modifiedAt;
    private Boolean isDone;
    private Long userId;
}
