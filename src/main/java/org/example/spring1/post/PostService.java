package org.example.spring1.post;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exceptions.EntityNotFoundException;
import org.example.spring1.photo.Photo;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.example.spring1.user.UserService;
import org.example.spring1.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(postMapper::toItemDto).toList();
    }

    public ResponseEntity<?> get(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toItemDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public PostDTO create(PostRequestDTO dto) {

        User user = userService.findById(dto.getUserId());
        Post post = postMapper.toEntity(dto);
        post.setUser(user);
        return postMapper.toItemDto(postRepository.save(post));
    }

    public void delete(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found"));

        userService.removePost(post.getUser().getId(), post);

        postRepository.deleteById(id);
    }

    public PostDTO update(Long id, PostRequestDTO dto) {
        return postRepository.findById(id)
                .map(item -> {
                    item.setName(dto.getName());
                    item.setDescription(dto.getDescription());
                    return postMapper.toItemDto(postRepository.save(item));
                })
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }


    public PostDTO changeName(Long id, String newName) {
        return postRepository.findById(id)
                .map(item -> {
                    item.setName(newName);
                    return postMapper.toItemDto(postRepository.save(item));
                })
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public void deleteMultiple(List<Long> ids) {
        ids.forEach(postRepository::deleteById);
    }

    public PostDTO changeDescription(Long id, String newDescription) {
        return postRepository.findById(id)
                .map(item -> {
                    item.setDescription(newDescription);
                    return postMapper.toItemDto(postRepository.save(item));
                })
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return postRepository.existsByName(name);
    }

    public PostDTO updatePhotoId(Long postId, Photo photo) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        post.setPhoto(photo);
        return postMapper.toItemDto(postRepository.save(post));
    }

    public List<PostDTO> getLikedPosts(Long id) {

        return postRepository.findAllByUserId(id);
    }
}