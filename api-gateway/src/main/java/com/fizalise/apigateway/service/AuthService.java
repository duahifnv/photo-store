package com.fizalise.apigateway.service;

import com.fizalise.apigateway.dto.AuthenticationRequest;
import com.fizalise.apigateway.dto.JwtResponse;
import com.fizalise.apigateway.dto.RegistrationRequest;
import com.fizalise.apigateway.entity.Role;
import com.fizalise.apigateway.entity.User;
import com.fizalise.apigateway.exception.CustomBadCredentialsException;
import com.fizalise.apigateway.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public record AuthService(JwtService jwtService,
                          UserService userService,
                          UserMapper userMapper,
                          AuthenticationManager authenticationManager) {
    public JwtResponse registerNewUser(RegistrationRequest registrationRequest) {
        User user = userService.create(
                userMapper.toUser(registrationRequest, Role.ROLE_USER)
        );
        return new JwtResponse(
                jwtService.generateToken(user)
        );
    }
    public JwtResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.username(),
                            authenticationRequest.password()
                    )
            );
            return new JwtResponse(
                    jwtService.generateToken(
                            userService().getUserByUsername(authenticationRequest.username())
                    )
            );
        } catch (BadCredentialsException e) {
            throw new CustomBadCredentialsException();
        }
    }
}
