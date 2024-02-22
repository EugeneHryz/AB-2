package com.example.hw1.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String birthDate;
}
