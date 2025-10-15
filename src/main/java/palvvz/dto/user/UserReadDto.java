package palvvz.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReadDto {
    private Long id;
    private String email;
    // Password intentionally omitted in response DTO for safety
}
