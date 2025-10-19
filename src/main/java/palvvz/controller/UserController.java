package palvvz.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import palvvz.dto.user.UserRequestDto;
import palvvz.dto.user.UserResponseDto;
import palvvz.mapper.UserMapper;
import palvvz.repository.UserRepository;
import palvvz.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> getAll() {
        var res = userRepository.findAll().stream().map(userMapper::toDto).toList();
        return res;
    }
}
