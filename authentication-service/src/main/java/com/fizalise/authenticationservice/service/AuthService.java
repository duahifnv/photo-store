package com.fizalise.authenticationservice.service;

import com.fizalise.authenticationservice.dto.AuthenticationRequest;
import com.fizalise.authenticationservice.dto.JwtResponse;
import com.fizalise.authenticationservice.dto.RegistrationRequest;
import com.fizalise.authenticationservice.dto.UserAuthorities;
import com.fizalise.authenticationservice.entity.Role;
import com.fizalise.authenticationservice.entity.User;
import com.fizalise.authenticationservice.exception.CustomBadCredentialsException;
import com.fizalise.authenticationservice.exception.InvalidTokenException;
import com.fizalise.authenticationservice.mapper.UserMapper;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public UserAuthorities getAuthorities(String jwt) {
        try {
            String username = jwtService.extractUsername(jwt);
            Collection<String> authorities = jwtService.extractRoles(jwt).stream().toList();
            return new UserAuthorities(username, authorities);
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }
}
