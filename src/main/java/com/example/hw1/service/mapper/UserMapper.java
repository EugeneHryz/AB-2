package com.example.hw1.service.mapper;

import com.example.hw1.repository.model.User;
import com.example.hw1.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto dto);
}
