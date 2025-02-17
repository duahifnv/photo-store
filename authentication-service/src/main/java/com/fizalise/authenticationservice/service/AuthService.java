package com.fizalise.authenticationservice.service;

import com.fizalise.authenticationservice.dto.AuthenticationRequest;
import com.fizalise.authenticationservice.dto.JwtResponse;
import com.fizalise.authenticationservice.dto.RegistrationRequest;
import com.fizalise.authenticationservice.dto.UserAuthorities;
import com.fizalise.authenticationservice.entity.Role;
import com.fizalise.authenticationservice.entity.User;
import com.fizalise.authenticationservice.exception.CustomBadCredentialsException;
import com.fizalise.authenticationservice.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public UserAuthorities getAuthorities(String jwt) {
        return new UserAuthorities(jwtService().extractUsername(jwt),
                jwtService.extractRoles(jwt).stream().toList());
    }
}
