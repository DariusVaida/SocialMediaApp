package org.example.spring1.user;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserDTO create(UserRequestDTO userRequestDTO) {
        return userMapper.toUserDTO(userRepository.save(userMapper.toEntity(userRequestDTO)));
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
                .orElse(null);
    }

    public UserDTO changeName(Long id, String newUsername) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUsername);
                    return userMapper.toUserDTO(userRepository.save(user));
                })
                .orElse(null);
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

}
