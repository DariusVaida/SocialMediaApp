package org.example.spring1.post;

import lombok.RequiredArgsConstructor;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> findAll() {
        return postService.findAll();
    }

    @PostMapping("/create")
    public PostDTO create(@RequestBody PostRequestDTO dto) {
        return postService.create(dto);
    }

    @DeleteMapping("/delete"+ID_PART)
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @DeleteMapping
    public void deleteMultiple(@RequestParam List<Long> ids) {
        postService.deleteMultiple(ids);
    }

    @PutMapping(ID_PART)
    public PostDTO update(@PathVariable Long id, @RequestBody PostRequestDTO dto) {
        return postService.update(id, dto);
    }

    @PatchMapping(ID_PART + CHANGE_NAME_PART)
    public PostDTO changeName(@PathVariable Long id, @RequestBody SingleBodyRequestDTO<String> dto) {
        return postService.changeName(id, dto.getBody());
    }
}
