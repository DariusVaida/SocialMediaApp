package org.example.spring1.post;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private PostController postController;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    void findAll() {

        assertEquals(0, postRepository.findAll().size());

        postRepository.save(Post.builder().name("whatever").build());

        assertEquals(1, postRepository.findAll().size());
    }

    @Test
    void create() {

        PostRequestDTO postDTO = PostRequestDTO.builder()
                .name("name")
                .description("description")
                .build();

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        postRepository.save(post);

        List<PostDTO> posts = postController.findAll();

        assertEquals(1, posts.size());
        assertEquals("name", posts.get(0).getName());
        assertEquals("description", posts.get(0).getDescription());
    }

    @Test
    void delete() {

        assertEquals(0, postRepository.findAll().size());

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        postRepository.save(post);

        assertEquals(1, postRepository.findAll().size());

        postRepository.delete(post);

        assertEquals(0, postRepository.findAll().size());

    }

    @Test
    void deleteMultiple() {

        assertEquals(0, postRepository.findAll().size());

        postRepository.save(Post.builder().name("whatever").build());
        postRepository.save(Post.builder().name("whatever2").build());

        assertEquals(2, postRepository.findAll().size());

        postRepository.deleteAll();

        assertEquals(0, postRepository.findAll().size());

    }

}