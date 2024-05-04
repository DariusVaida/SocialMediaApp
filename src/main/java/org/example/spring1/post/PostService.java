package org.example.spring1.post;

import lombok.RequiredArgsConstructor;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(postMapper::toItemDto).toList();
    }

    public ResponseEntity<?> get(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toItemDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public PostDTO create(PostRequestDTO dto) {
        return postMapper.toItemDto(postRepository.save(postMapper.toEntity(dto)));
    }

    public void delete(Long id) {

        postRepository.deleteById(id);

    }

    public List<PostDTO> findAllFiltered(String name) {
        return postRepository.findAllByName(name);
    }

    public PostDTO update(Long id, PostRequestDTO dto) {
        return postRepository.findById(id)
                .map(item -> {
                    item.setName(dto.getName());
                    item.setDescription(dto.getDescription());
                    return postMapper.toItemDto(postRepository.save(item));
                })
                .orElse(null);
    }

    public PostDTO changeName(Long id, String newName) {
        return postRepository.findById(id)
                .map(item -> {
                    item.setName(newName);
                    return postMapper.toItemDto(postRepository.save(item));
                })
                .orElse(null);
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
                .orElse(null);
    }
}