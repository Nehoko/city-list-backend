package ru.idmikhailov.citylist.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.idmikhailov.citylist.dto.UserDto;
import ru.idmikhailov.citylist.dto.UserWithTokenDto;
import ru.idmikhailov.citylist.service.TokenService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:80"}, maxAge = 3600)
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public UserWithTokenDto token(Authentication authentication) {
        if (authentication == null) return null;
        log.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token generated {}", token);

        log.debug("Collecting data about user: '{}'", authentication.getName());
        User principal = (User) authentication.getPrincipal();
        String password = principal.getPassword();

        Optional<Collection<GrantedAuthority>> authorities = Optional.ofNullable(principal.getAuthorities());
        List<String> roleList = new ArrayList<>();
        if (authorities.isPresent()) {
            List<GrantedAuthority> authorityList = authorities.get().stream().toList();
            if (!authorityList.isEmpty()) {
                roleList = authorityList.stream().map(GrantedAuthority::getAuthority).toList();
            }
        }
        UserWithTokenDto userWithTokenDto = new UserWithTokenDto(token, new UserDto(authentication.getName(), password, roleList));
        log.debug("Data collected: {}", userWithTokenDto);
        return userWithTokenDto;
    }


    @GetMapping("/me")
    public UserWithTokenDto getMe(@AuthenticationPrincipal Jwt principal) {
        if (principal == null) return null;
        log.debug("User requested for token: '{}'", principal.getTokenValue());
        List<String> roleList = principal.getClaimAsStringList("scope");
        UserWithTokenDto user = new UserWithTokenDto(principal.getTokenValue(), new UserDto(principal.getClaimAsString("sub"), null, roleList));
        log.debug("User granted {}", user);
        return user;
    }
}
