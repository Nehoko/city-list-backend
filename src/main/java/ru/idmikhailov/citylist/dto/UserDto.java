package ru.idmikhailov.citylist.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private final String username;
    private final String password;
    private final List<String> roles;
}
