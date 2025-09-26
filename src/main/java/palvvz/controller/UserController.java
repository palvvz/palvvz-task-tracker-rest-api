package palvvz.controller;

import palvvz.MainProperties;
import palvvz.dto.user.UserCreateEditDto;
import palvvz.dto.user.UserReadDto;

import palvvz.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("${app.main.endpoint}/users")
//@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MainProperties mainProperties;

    public UserController(UserService userService, MainProperties mainProperties) {
        this.userService = userService;
        this.mainProperties = mainProperties;
    }


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

//    @PostMapping
//    public String create(@RequestBody Map<String, String> fields) {
//
//        return "";
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    public UserReadDto create(@ModelAttribute UserCreateEditDto userDto) {
//        UserReadDto userReadDto =  userService.create(userDto);
//        return userReadDto;
//    }

//    // @Valid @RequestBody
//    @PostMapping
//    public ResponseEntity<UserReadDto> create(@RequestBody UserReadDto user) {
//        UserReadDto dto = userService.create(user);
//        return ResponseEntity.created(URI.create("/api/users/" + dto.getId())).body(dto);
//    }
//
//    @PutMapping("/{id}")
//    public UserReadDto update(@PathVariable Long id, @Valid @RequestBody UpdateUserDto request) {
//        return userService.update(id, request);
//    }
//
//    @PatchMapping("/{id}")
//    public UserReadDto patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
//        return userService.patch(id, fields);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
