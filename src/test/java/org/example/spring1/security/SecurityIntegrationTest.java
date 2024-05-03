package org.example.spring1.security;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.security.dto.LoginRequest;
import org.example.spring1.security.dto.SignupRequest;
import org.example.spring1.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
    @Test
    void authenticateUser() {

        SignupRequest user = SignupRequest.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        authController.registerUser(user);

        LoginRequest loginRequest = new LoginRequest("username", "password");

        authController.authenticateUser(loginRequest);

        assertTrue(authController.authenticateUser(loginRequest).getStatusCode() == HttpStatusCode.valueOf(200));


    }

    @Test
    void registerUser() {

        SignupRequest user = SignupRequest.builder()
                .username("username1")
                .password("password")
                .email("email2")
                .build();

        authController.registerUser(user);

        assertSame(authController.registerUser(user).getStatusCode(), HttpStatusCode.valueOf(200));

    }
}
