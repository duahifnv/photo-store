package com.fizalise.apigateway.service;

import com.fizalise.apigateway.entity.Role;
import com.fizalise.apigateway.entity.User;
import com.fizalise.apigateway.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTest {
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:17.2")
                    .withCopyFileToContainer(
                            MountableFile.forClasspathResource("init-db.sql"),
                            "/docker-entrypoint-initdb.d/"
                    );
    static {
        postgreSQLContainer.start();
    }
    @BeforeEach
    void populateSchema() {}
    @Test
    void getUserByUsername() {
        User createdUser = userRepository.save(User.builder()
                .username("username")
                .password("password")
                .email("user@mail.ru")
                .name("Jane")
                .surname("Doe")
                .role(Role.ROLE_USER)
                .build());
        assertTrue(new ReflectionEquals(createdUser).matches(
                userService.getUserByUsername("username")
        ));
    }
    @Test
    void getUserByEmail() {
    }
    @Test
    void save() {
    }
    @Test
    void create() {
    }
}