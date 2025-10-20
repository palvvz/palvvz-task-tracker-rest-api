package palvvz.auth;

import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import palvvz.dto.user.UserRequestDto;
import palvvz.dto.user.UserResponseDto;
import palvvz.jwt.JwtService;
import palvvz.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;


    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody UserRequestDto request) {
        var loginResult = authService.login(request);
        var refreshToken = loginResult.getRefreshToken().toString();
        return new JwtResponse(loginResult.getAccessToken().toString());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto request) {
        var userDto = userService.register(request);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String header) {
        var token = header.replace("Bearer ", "");
        return jwtService.validateToken(token);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
