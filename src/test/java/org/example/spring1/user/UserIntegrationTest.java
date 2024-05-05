package org.example.spring1.user;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @BeforeEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findAll() {

        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        userRepository.save(user);

        assertEquals(1, userController.findAll().size());
    }

    @Test
    void create() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();

        userRepository.save(user);

        assertEquals(1, userRepository.findAll().size());
    }

}
