package org.example.spring1.user;


import lombok.RequiredArgsConstructor;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.post.PostService;
import org.example.spring1.post.model.Post;
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

    private final PostService postService;


    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @DeleteMapping(ID_PART)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserRequestDTO dto) {
        return userService.create(dto);
    }

    @PostMapping("/like"+ ID_PART)
    public UserDTO like(@PathVariable Long id, @RequestParam("postId") Long postId){
        Post post = postService.findById(postId);
        return userService.like(id, post);
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
