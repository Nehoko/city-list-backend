package ru.idmikhailov.citylist.dto;

import lombok.Data;

@Data
public class UserDto {
    private final String username;
    private final String password;
}