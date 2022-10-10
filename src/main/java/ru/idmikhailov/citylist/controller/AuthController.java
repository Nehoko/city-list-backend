package ru.idmikhailov.citylist.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.idmikhailov.citylist.dto.UserDto;
import ru.idmikhailov.citylist.dto.UserWithTokenDto;
import ru.idmikhailov.citylist.service.TokenService;

@Log4j2
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public UserWithTokenDto token(Authentication authentication) {
        if (authentication == null) return null;
        log.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granted {}", token);
        return new UserWithTokenDto(token, new UserDto(authentication.getName(), ((User)authentication.getPrincipal()).getPassword()));
    }
}
