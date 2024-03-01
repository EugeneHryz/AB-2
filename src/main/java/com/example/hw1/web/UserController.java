package com.example.hw1.web;

import com.example.hw1.service.ImageService;
import com.example.hw1.service.UserService;
import com.example.hw1.service.dto.ImageDto;
import com.example.hw1.service.dto.UserDto;
import com.example.hw1.service.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @QueryMapping
    public UserDto userById(@Argument Long id) {
        return userService.findById(id);
    }

    @QueryMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @SchemaMapping(typeName="User", field="images")
    public List<ImageDto> getUserImages(UserDto user) {
        return imageService.getAllByUser(user.getId());
    }

    @MutationMapping
    public UserDto createUser(@Argument UserDto user) throws UnprocessableEntityException {
        return userService.createUser(user);
    }
}
