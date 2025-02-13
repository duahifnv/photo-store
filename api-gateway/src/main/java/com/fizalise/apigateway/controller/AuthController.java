package com.fizalise.apigateway.controller;

import com.fizalise.apigateway.dto.AuthenticationRequest;
import com.fizalise.apigateway.dto.JwtResponse;
import com.fizalise.apigateway.dto.RegistrationRequest;
import com.fizalise.apigateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO: 13.02.2025 add global exception handler
    // TODO: 13.02.2025 add field validation
    public JwtResponse registerNewUser(@RequestBody RegistrationRequest registrationRequest) {
        return authService.registerNewUser(registrationRequest);
    }
    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }
}
