package palvvz.dto.user;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserReadDto {
    private Long id;
    private String email;
    // Password intentionally omitted in response DTO for safety
}
