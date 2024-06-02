package org.example.spring1.user;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.post.PostService;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest extends SpringControllerBaseTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        userController = new UserController(userService, postService);
        mvc = buildForController(userController);
    }

    @Test
    void findAll() {


        when(userService.findAll()).thenReturn(List.of(UserDTO.builder().username("username").build()));

        List<UserDTO> users = userController.findAll();

        assertEquals(1, users.size());

    }

    @Test
    void create() {

        UserRequestDTO userDTO = UserRequestDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(userService.create(userDTO)).thenReturn(UserDTO.builder().username("username").build());

        UserDTO result = userController.create(userDTO);

        assertEquals("username", result.getUsername());
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

    @Test
    void update() {


        UserRequestDTO userDTO = UserRequestDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(userService.update(1L, userDTO)).thenReturn(UserDTO.builder().username("username").build());

        UserDTO result = userController.update(1L, userDTO);

        assertEquals("username", result.getUsername());
    }

    @Test
    void changeName() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        when(userService.changeName(1L, "newUsername")).thenReturn(UserDTO.builder().username("newUsername").build());

        SingleBodyRequestDTO singleBodyRequestDTO = new SingleBodyRequestDTO("newUsername");
        UserDTO result = userController.changeName(1L, singleBodyRequestDTO);

        assertEquals("newUsername", result.getUsername());
    }
}