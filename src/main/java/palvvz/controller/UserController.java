package palvvz.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import palvvz.dto.user.UserCreateEditDto;
import palvvz.dto.user.UserReadDto;
import palvvz.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.main.endpoint}/users")

public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserReadDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateEditDto request) {
        UserReadDto dto = userService.create(request);
        return ResponseEntity.created(URI.create("/api/users/" + dto.getId())).body(dto);
    }

}
