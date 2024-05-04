package org.example.spring1.user;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void findAll() {

        assertEquals(0, userService.findAll().size());

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        userService.create(UserRequestDTO.builder()
                .username("username")
                .password("password")
                .build());

        assertEquals(1, userService.findAll().size());


    }

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
    void create() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("username")
                .password("password")
                .build();

        UserDTO result = userService.create(userRequestDTO);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void delete() {

        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.delete(1L);

        assertFalse(userRepository.findById(1L).isPresent());
    }

    @Test
    void update() {

        User user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .username("newusername")
                .password("newpassword")
                .build();

        UserDTO result = userService.update(1L, userRequestDTO);

        assertEquals(user.getId(), result.getId());
        assertEquals(userRequestDTO.getUsername(), result.getUsername());
        assertEquals(userRequestDTO.getPassword(), result.getPassword());
    }

    @Test
    void changeName() {

            User user = User.builder()
                    .id(1L)
                    .username("username")
                    .password("password")
                    .email("email")
                    .build();

            when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

            UserDTO result = userService.changeName(1L, "newusername");

            assertEquals(user.getId(), result.getId());
            assertEquals("newusername", result.getUsername());
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

    @Test
    void deleteMultiple() {

        User user1 = User.builder()
                .id(1L)
                .username("username1")
                .password("password1")
                .email("email1")
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("username2")
                .password("password2")
                .email("email2")
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(user2));

        userService.deleteMultiple(List.of(1L, 2L));

        assertFalse(userRepository.findById(1L).isPresent());
        assertFalse(userRepository.findById(2L).isPresent());
    }
}