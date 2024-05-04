package org.example.spring1.security;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthServiceTest extends SpringUnitBaseTest {


    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Test
    void existsByUsername() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();


        when(userRepository.existsByUsername("username")).thenReturn(true);

        assertTrue(authService.existsByUsername("username"));

        assertFalse(authService.existsByUsername("notusername"));

    }

    @Test
    void existsByEmail() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.existsByEmail("email")).thenReturn(true);

        assertTrue(authService.existsByEmail("email"));

        assertFalse(authService.existsByEmail("notemail"));
    }

    @Test
    void register() {
    }

    @Test
    void authenticate() {
    }
}