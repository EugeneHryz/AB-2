package com.example.hw1.service;

import com.example.hw1.repository.UserRepository;
import com.example.hw1.repository.model.Operation;
import com.example.hw1.repository.model.User;
import com.example.hw1.service.dto.OperationDto;
import com.example.hw1.service.dto.UserDto;
import com.example.hw1.service.exception.EntityNotFoundException;
import com.example.hw1.service.exception.UnprocessableEntityException;
import com.example.hw1.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final OperationService operationService;
    private final UserMapper userMapper;

    public UserDto createUser(UserDto dto) throws UnprocessableEntityException {
        Optional<User> sameUsernameUser = userRepository.findByUsername(dto.getUsername());
        if (sameUsernameUser.isPresent()) {
            throw new UnprocessableEntityException("Username is already taken");
        }
        User createdUser = userRepository.save(userMapper.userDtoToUser(dto));

        operationService.logOperation(new OperationDto(
                String.format("Created new user: %s", createdUser),
                Operation.OperationType.WRITE
        ));

        return userMapper.userToUserDto(createdUser);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

        return userMapper.userToUserDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .toList();
    }
}
