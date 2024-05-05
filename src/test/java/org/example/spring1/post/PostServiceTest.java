package org.example.spring1.post;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PostServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;


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



}