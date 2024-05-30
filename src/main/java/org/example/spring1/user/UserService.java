package org.example.spring1.user;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exceptions.EntityNotFoundException;
import org.example.spring1.post.PostService;
import org.example.spring1.post.model.Post;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }

    public UserDTO create(UserRequestDTO userRequestDTO) {
        return userMapper.toUserDTO(userRepository.save(userMapper.toEntity(userRequestDTO)));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO update(Long id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userRequestDTO.getUsername());
                    user.setPassword(userRequestDTO.getPassword());
                    return userMapper.toUserDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserDTO changeName(Long id, String newUsername) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUsername);
                    return userMapper.toUserDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void deleteMultiple(List<Long> ids) {
        ids.forEach(userRepository::deleteById);
    }

    public UserDTO like(Long id, Post post) {

        User user = findById(id);

        user.getLikedPosts().add(post);

        return userMapper.toUserDTO(userRepository.save(user));
    }
}
