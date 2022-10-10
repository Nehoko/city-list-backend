package ru.idmikhailov.citylist.dto;

import lombok.Data;

@Data
public class UserWithTokenDto {
    private final String jwt;
    private final UserDto user;
}
