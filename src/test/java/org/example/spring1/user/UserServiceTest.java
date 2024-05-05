package org.example.spring1.user;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.user.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void findById() {

        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User result = userService.findById(1L);

        assertEquals(user, result);
    }

    @Test
    void findByUsername() {

        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.findByUsername("username")).thenReturn(java.util.Optional.of(user));

        User result = userService.findByUsername("username");

        assertEquals(user, result);
    }


    @Test
    void existsById() {

        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.existsById(1L)).thenReturn(true);

        assertTrue(userService.existsById(1L));

        assertFalse(userService.existsById(2L));
    }

    @Test
    void existsByUsername() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.existsByUsername("username")).thenReturn(true);

        assertTrue(userService.existsByUsername("username"));
    }
}