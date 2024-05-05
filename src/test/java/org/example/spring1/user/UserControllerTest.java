package org.example.spring1.user;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.*;

class UserControllerTest extends SpringControllerBaseTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        userController = new UserController(userService);
        mvc = buildForController(userController);
    }


    @Test
    void delete() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userService.findById(1L)).thenReturn(user);


        userController.delete(1L);

        verify(userService, times(1)).delete(1L);
    }

    @Test
    void deleteMultiple() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userService.findById(1L)).thenReturn(user);

        userController.deleteMultiple(List.of(1L));

        verify(userService, times(1)).deleteMultiple(List.of(1L));
    }
}