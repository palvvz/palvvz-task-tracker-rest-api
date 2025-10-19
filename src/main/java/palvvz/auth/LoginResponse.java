package palvvz.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import palvvz.jwt.Jwt;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
