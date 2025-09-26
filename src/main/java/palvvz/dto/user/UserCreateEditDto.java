package palvvz.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserCreateEditDto {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;
}
