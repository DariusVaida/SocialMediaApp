package org.example.spring1.security;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.security.dto.LoginRequest;
import org.example.spring1.security.dto.SignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private AuthController authController;

    @Test
    void authenticateUser() {

        LoginRequest user = new LoginRequest("username", "password");

        authController.authenticateUser(user);

        assertTrue(authController.authenticateUser(user).getStatusCode() == HttpStatusCode.valueOf(200));


    }

    @Test
    void registerUser() {
        SignupRequest user = SignupRequest.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        authController.registerUser(user);

        assertTrue(authController.registerUser(user).getStatusCode() == HttpStatusCode.valueOf(200));

    }
}
