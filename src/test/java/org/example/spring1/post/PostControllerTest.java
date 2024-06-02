package org.example.spring1.post;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.photo.PhotoService;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest extends SpringControllerBaseTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @Mock
    private PhotoService photoService;


    @BeforeEach
    public void setUp() {
        super.setUp();
        postController = new PostController(postService, photoService);
        mvc = buildForController(postController);
    }

    @Test
    void get() {

        PostDTO postDTO = PostDTO.builder()
                .name("name")
                .description("description")
                .build();

        List<PostDTO> postDTOList = List.of(postDTO);

        when(postService.getLikedPosts(1L)).thenReturn(postDTOList);

        List<PostDTO> posts = postController.get(1L);

        PostDTO result = posts.get(0);

        assertEquals("name", result.getName());
        assertEquals("description", result.getDescription());
    }

    @Test
    void findAll() {


        when(postService.findAll()).thenReturn(List.of(PostDTO.builder().name("whatever").build()));

        List<PostDTO> posts = postController.findAll();

        assertEquals(1, posts.size());


    }

    @Test
    void create() {

        PostRequestDTO postDTO = PostRequestDTO.builder()
                .name("name")
                .description("description")
                .build();

        when(postService.create(postDTO)).thenReturn(PostDTO.builder().name("name").description("description").build());

        PostDTO result = postController.create(postDTO);

        assertEquals("name", result.getName());
        assertEquals("description", result.getDescription());
    }

    @Test
    void delete() {

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postService.findById(1L)).thenReturn(post);

        postController.delete(1L);

        verify(postService, times(1)).delete(1L);
    }

    @Test
    void deleteMultiple() {

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postService.findById(1L)).thenReturn(post);

        postController.deleteMultiple(List.of(1L));

        verify(postService, times(1)).deleteMultiple(List.of(1L));
    }

    @Test
    void update() {

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postService.findById(1L)).thenReturn(post);

        PostRequestDTO postDTO = PostRequestDTO.builder()
                .name("name")
                .description("description")
                .build();

        postController.update(1L, postDTO);

        verify(postService, times(1)).update(1L, postDTO);
    }

    @Test
    void changeName() {

        Post post = Post.builder()
                .name("name")
                .description("description")
                .build();

        when(postService.findById(1L)).thenReturn(post);

        SingleBodyRequestDTO postDTO = new SingleBodyRequestDTO("newName");

        postController.changeName(1L, postDTO);

        verify(postService, times(1)).changeName(1L, "newName");
    }
}