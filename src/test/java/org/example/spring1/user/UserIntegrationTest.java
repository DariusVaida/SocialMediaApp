package org.example.spring1.user;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


}
