package org.example.spring1.user;

import org.example.spring1.user.model.User;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toEntity(UserRequestDTO userDTO);

    UserRequestDTO toUserResponseDTO(User user);
}
