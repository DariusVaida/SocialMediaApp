package org.example.spring1.post;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PostServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;


    @Test
    void findById() {

        Post post = Post.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));

        Post result = postService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("name", result.getName());
        assertEquals("description", result.getDescription());
    }


    @Test
    void delete() {

        Post post = Post.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));

        postService.delete(1L);

        assertEquals(0, postRepository.findAll().size());
    }

    @Test
    void deleteMultiple() {

        Post post1 = Post.builder()
                .id(1L)
                .name("name1")
                .description("description1")
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .name("name2")
                .description("description2")
                .build();

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post1));
        when(postRepository.findById(2L)).thenReturn(java.util.Optional.of(post2));

        postService.deleteMultiple(List.of(1L, 2L));

        assertEquals(0, postRepository.findAll().size());
    }

    @Test
    void existsById() {

        Post post = Post.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        when(postRepository.existsById(1L)).thenReturn(true);

        assertTrue(postService.existsById(1L));

    }

    @Test
    void existsByName() {

        Post post = Post.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        when(postRepository.existsByName("name")).thenReturn(true);

        assertTrue(postService.existsByName("name"));

    }

    @Test
    void create(){

            PostRequestDTO postRequestDTO = PostRequestDTO.builder()
                    .name("name")
                    .description("description")
                    .build();

            Post post = Post.builder()
                    .id(1L)
                    .name("name")
                    .description("description")
                    .build();

            when(postRepository.save(post)).thenReturn(post);

            PostDTO postDTO = PostDTO.builder()
                    .id(1L)
                    .name("name")
                    .description("description")
                    .build();

            when(postMapper.toEntity(postRequestDTO)).thenReturn(post);
            when(postMapper.toItemDto(post)).thenReturn(postDTO);

            PostDTO result = postService.create(postRequestDTO);

            assertEquals(1L, result.getId());
            assertEquals("name", result.getName());
            assertEquals("description", result.getDescription());
    }

}