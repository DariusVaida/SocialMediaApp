package org.example.spring1.security;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.security.dto.LoginRequest;
import org.example.spring1.security.dto.SignupRequest;
import org.example.spring1.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

import static org.example.spring1.user.model.ERole.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
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


        //I don't understand why the test is failing
        SignupRequest user = SignupRequest.builder()
                .username("username123")
                .email("email223")
                .password("password12")
                .roles(Set.of(CUSTOMER.name()))
                .build();

        assertSame(HttpStatusCode.valueOf(200), authController.registerUser(user).getStatusCode());

    }
}
