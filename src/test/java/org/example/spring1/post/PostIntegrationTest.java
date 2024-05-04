package org.example.spring1.post;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PostIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private PostController postController;

    @Autowired
    private PostRepository postRepository;

    @Test
    void findAllByName() {
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

        when(postRepository.save(post)).thenReturn(post);

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

        assertEquals(1, postRepository.findAll().size());

        postRepository.delete(post);

        assertEquals(0, postRepository.findAll().size());
        
    }

    @Test
    void update() {

        PostRequestDTO postDTO = PostRequestDTO.builder()
                .name("name")
                .description("description")
                .build();

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postRepository.save(post)).thenReturn(post);

        postRepository.create(postDTO);

        List<PostDTO> posts = postRepository.findAll();

        assertEquals(1, posts.size());
        assertEquals("name", posts.get(0).getName());
        assertEquals("description", posts.get(0).getDescription());

        PostRequestDTO postDTO2 = PostRequestDTO.builder()
                .name("name2")
                .description("description2")
                .build();

        Post post2 = Post.builder()
                .name("name2")
                .description("description2")
                .build();

        when(postRepository.save(post2)).thenReturn(post2);

        postRepository.update(1L, postDTO2);

        List<PostDTO> posts2 = postRepository.findAll();

        assertEquals(1, posts2.size());
        assertEquals("name2", posts2.get(0).getName());
        assertEquals("description2", posts2.get(0).getDescription());
    }

    @Test
    void changeName() {

        PostRequestDTO postDTO = PostRequestDTO.builder()
                .name("name")
                .description("description")
                .build();

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postRepository.save(post)).thenReturn(post);

        postRepository.create(postDTO);

        List<PostDTO> posts = postRepository.findAll();

        assertEquals(1, posts.size());
        assertEquals("name", posts.get(0).getName());
        assertEquals("description", posts.get(0).getDescription());

        when(postRepository.save(post)).thenReturn(post);

        postRepository.changeName(1L, "name2");

        List<PostDTO> posts2 = postRepository.findAll();

        assertEquals(1, posts2.size());
        assertEquals("name2", posts2.get(0).getName());
        assertEquals("description", posts2.get(0).getDescription());
    }

    @Test
    void deleteMultiple() {

        assertEquals(0, postRepository.findAll().size());

        postRepository.save(Post.builder().name("whatever").build());
        postRepository.save(Post.builder().name("whatever2").build());

        assertEquals(2, postRepository.findAll().size());

        when(postRepository.save(Post.builder().name("whatever").build())).thenReturn(Post.builder().name("whatever").build());
        when(postRepository.save(Post.builder().name("whatever2").build())).thenReturn(Post.builder().name("whatever2").build());


    }

    @Test
    void changeDescription() {

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postRepository.save(post)).thenReturn(post);

        postRepository.save(post);

        when(postRepository.save(post)).thenReturn(post);

    }
}