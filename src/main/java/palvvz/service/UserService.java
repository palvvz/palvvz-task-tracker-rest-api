package palvvz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import palvvz.domain.User;
import palvvz.dto.user.UserCreateEditDto;
import palvvz.dto.user.UserReadDto;
import palvvz.exception.ResourceNotFoundException;
import palvvz.mapper.UserMapper;
import palvvz.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public List<UserReadDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserReadDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return userMapper.toDto(user);
    }

    public UserReadDto create(UserCreateEditDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = userMapper.fromCreate(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
//
//    @Override
//    public UserReadDto update(Long id, UpdateUserDto request) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
//
//        // enforce email uniqueness on update
//        userRepository.findByEmail(request.getEmail())
//                .filter(u -> !u.getId().equals(id))
//                .ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });
//
//        userMapper.update(user, request);
//        return userMapper.toDto(user);
//    }
//
//    @Override
//    public UserReadDto patch(Long id, Map<String, Object> fields) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
//        // Merge incoming fields into a new UpdateUserDto
//        UpdateUserDto req = new UpdateUserDto(user.getEmail(), user.getPassword());
//        try {
//            objectMapper.updateValue(req, fields);
//        } catch (JsonMappingException e) {
//            throw new RuntimeException(e);
//        }
//        // validate email uniqueness
//        userRepository.findByEmail(req.getEmail())
//                .filter(u -> !u.getId().equals(id))
//                .ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });
//        userMapper.update(user, req);
//        return userMapper.toDto(user);
//    }
//
//    @Override
//    public void delete(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new ResourceNotFoundException("User not found: " + id);
//        }
//        userRepository.deleteById(id);
//    }
}