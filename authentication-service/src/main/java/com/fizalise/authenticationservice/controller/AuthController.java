package com.fizalise.authenticationservice.controller;

import com.fizalise.authenticationservice.dto.AuthenticationRequest;
import com.fizalise.authenticationservice.dto.JwtResponse;
import com.fizalise.authenticationservice.dto.RegistrationRequest;
import com.fizalise.authenticationservice.dto.UserAuthorities;
import com.fizalise.authenticationservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse registerNewUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return authService.registerNewUser(registrationRequest);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }
    @GetMapping("/authorities")
    @ResponseStatus(HttpStatus.OK)
    public UserAuthorities getAuthorities(@RequestParam String jwt) {
        return authService.getAuthorities(jwt);
    }
}
