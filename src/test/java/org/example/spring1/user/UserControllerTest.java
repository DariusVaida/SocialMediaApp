package org.example.spring1.user;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
    void findAll() throws Exception {


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