package com.fizalise.authenticationservice.service;

import com.fizalise.authenticationservice.entity.User;
import com.fizalise.authenticationservice.exception.UserAlreadyExistsException;
import com.fizalise.authenticationservice.exception.UserNotFoundException;
import com.fizalise.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
    public UserDetailsService getUserDetailsService() {
        return this::getUserByUsername;
    }
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
    @Transactional
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        }
        return save(user);
    }
}
