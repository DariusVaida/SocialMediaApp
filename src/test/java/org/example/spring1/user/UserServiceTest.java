package org.example.spring1.user;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.exceptions.EntityNotFoundException;
import org.example.spring1.post.model.Post;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

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

    @Test
    void like() {
        //complicated to test
    }

    @Test
    void like_userNotFound() {
        Long userId = 1L;
        Post post = Post.builder()
                .id(2L)
                .name("Post Name")
                .description("Post Description")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.like(userId, post));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void findLikedPosts() {
        Long userId = 1L;
        Post post = Post.builder()
                .id(2L)
                .name("Post Name")
                .description("Post Description")
                .build();
        User user = User.builder()
                .id(userId)
                .username("username")
                .password("password")
                .email("email")
                .likedPosts(Set.of(post))
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<Post> result = userService.findLikedPosts(userId);

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findLikedPosts_userNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findLikedPosts(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void delete() {
        Long userId = 1L;
        userService.delete(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void findAll() {
        List<User> users = List.of(
                User.builder().id(1L).username("user1").build(),
                User.builder().id(2L).username("user2").build()
        );
        List<UserDTO> userDTOS = List.of(
                UserDTO.builder().id(1L).username("user1").build(),
                UserDTO.builder().id(2L).username("user2").build()
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserDTO(users.get(0))).thenReturn(userDTOS.get(0));
        when(userMapper.toUserDTO(users.get(1))).thenReturn(userDTOS.get(1));

        List<UserDTO> result = userService.findAll();

        assertEquals(userDTOS, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void create() {
        UserRequestDTO userRequestDTO = UserRequestDTO.builder().username("newUser").password("password").build();
        User user = User.builder().id(1L).username("newUser").password("password").build();
        UserDTO userDTO = UserDTO.builder().id(1L).username("newUser").build();

        when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.create(userRequestDTO);

        assertEquals(userDTO, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void changeName() {
        Long userId = 1L;
        String newUsername = "newUsername";
        User user = User.builder().id(userId).username("oldUsername").build();
        User updatedUser = User.builder().id(userId).username(newUsername).build();
        UserDTO userDTO = UserDTO.builder().id(userId).username(newUsername).build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toUserDTO(updatedUser)).thenReturn(userDTO);

        UserDTO result = userService.changeName(userId, newUsername);

        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteMultiple() {
        List<Long> userIds = List.of(1L, 2L, 3L);
        userService.deleteMultiple(userIds);
        verify(userRepository, times(1)).deleteById(1L);
        verify(userRepository, times(1)).deleteById(2L);
        verify(userRepository, times(1)).deleteById(3L);
    }

    @Test
    void removePost() {
    }

    @Test
    void update() {
        Long userId = 1L;
        UserRequestDTO userRequestDTO = UserRequestDTO.builder().username("updatedUser").password("password").build();
        User user = User.builder().id(userId).username("oldUser").password("password").build();
        User updatedUser = User.builder().id(userId).username("updatedUser").password("password").build();
        UserDTO userDTO = UserDTO.builder().id(userId).username("updatedUser").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toUserDTO(updatedUser)).thenReturn(userDTO);

        UserDTO result = userService.update(userId, userRequestDTO);

        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
}