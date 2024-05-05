package org.example.spring1.security;

import org.example.project.core.SpringControllerBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AuthControllerTest extends SpringControllerBaseTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private JwtUtilsService jwtUtilsService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        authController = new AuthController(authService, jwtUtilsService);
        mvc = buildForController(authController);
    }

    @Test
    void authenticateUser() {
    }

    @Test
    void registerUser() {
    }
}