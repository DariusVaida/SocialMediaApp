package org.example.spring1.user;


import lombok.RequiredArgsConstructor;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.user.model.dto.UserDTO;
import org.example.spring1.user.model.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @DeleteMapping(ID_PART)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @DeleteMapping
    public void deleteMultiple(@RequestParam List<Long> ids) {
        userService.deleteMultiple(ids);
    }

    @PutMapping(ID_PART)
    public UserDTO update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return userService.update(id, dto);
    }

    @PatchMapping(ID_PART + CHANGE_NAME_PART)
    public UserDTO changeName(@PathVariable Long id, @RequestBody SingleBodyRequestDTO<String> dto) {
        return userService.changeName(id, dto.getBody());
    }


}
