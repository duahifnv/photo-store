package com.fizalise.authenticationservice.controller;

import com.fizalise.authenticationservice.dto.*;
import com.fizalise.authenticationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "Зарегистрировать нового пользователя",
            description = "Возвращает сгенерированный JWT-токен")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse registerNewUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return authService.registerNewUser(registrationRequest);
    }
    @Operation(summary = "Аутентифицировать существующего пользователя",
            description = "Возвращает сгенерированный JWT-токен")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }
    @Operation(summary = "Получить полномочия пользователя по его токену")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/authorities")
    @ResponseStatus(HttpStatus.OK)
    public UserAuthorities getAuthorities(@RequestParam String jwt) {
        return authService.getAuthorities(jwt);
    }
    @Operation(summary = "Получить информацию о пользователе по его имени")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo getUserInfo(@PathVariable String username) {
        return authService.getUserInfo(username);
    }
}
