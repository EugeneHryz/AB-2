package com.example.hw1.web;

import com.example.hw1.repository.UserRepository;
import com.example.hw1.repository.model.User;
import com.example.hw1.service.UserService;
import com.example.hw1.service.dto.UserDto;
import com.example.hw1.service.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public UserDto userById(@Argument Long id) {
        return userService.findById(id);
    }

    @QueryMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @MutationMapping
    public UserDto createUser(@Argument UserDto user) throws UnprocessableEntityException {
        return userService.createUser(user);
    }
}
