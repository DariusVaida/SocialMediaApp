package org.example.spring1.user;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void findAll() throws Exception{


    }

    @Test
    void create() {

        UserRequestDTO createRequestDto = UserRequestDTO.builder().username("username").password("password").build();

        UserDTO result = UserDTO.builder().id(1L).username("username").build();

        when(userService.create(createRequestDto))
                .thenReturn(result);

        assertEquals(result.getId(), userController.create(createRequestDto).getId());
        assertEquals(result.getUsername(), userController.create(createRequestDto).getUsername());

    }

    @Test
    void delete() {

        UserRequestDTO createRequestDto = UserRequestDTO.builder().username("username").password("password").build();


    }

    @Test
    void deleteMultiple() {
    }

    @Test
    void update() {
    }

    @Test
    void changeName() {
    }
}