package com.fizalise.apigateway.service;

import com.fizalise.apigateway.entity.User;
import com.fizalise.apigateway.exception.UserAlreadyExistsException;
import com.fizalise.apigateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Пользователь с таким именем не найден")
                );
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Пользователь с таким именем не найден")
                );
    }
    public UserDetailsService getUserDetailsService() {
        return this::getUserByUsername;
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        }
        this.save(user);
    }
}
