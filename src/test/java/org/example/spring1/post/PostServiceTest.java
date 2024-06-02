package org.example.spring1.post;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.photo.Photo;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PostServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

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
    void create() {

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

    @Test
    void findAll() {
        Post post = Post.builder().name("Sample Post").description("Sample Description").build();
        PostDTO postDTO = PostDTO.builder().name("Sample Post").description("Sample Description").build();

        when(postRepository.findAll()).thenReturn(List.of(post));
        when(postMapper.toItemDto(post)).thenReturn(postDTO);

        List<PostDTO> result = postService.findAll();

        assertEquals(1, result.size());
        assertEquals("Sample Post", result.get(0).getName());
        verify(postRepository, times(1)).findAll();
        verify(postMapper, times(1)).toItemDto(post);
    }

    @Test
    void get() {
        Long postId = 1L;
        Post post = Post.builder().id(postId).name("Sample Post").description("Sample Description").build();
        PostDTO postDTO = PostDTO.builder().id(postId).name("Sample Post").description("Sample Description").build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postMapper.toItemDto(post)).thenReturn(postDTO);

        ResponseEntity<?> response = postService.get(postId);

        assertEquals(postDTO, response.getBody());
        verify(postRepository, times(1)).findById(postId);
        verify(postMapper, times(1)).toItemDto(post);
    }

    @Test
    void changeName() {
        Long postId = 1L;
        String newName = "New Name";
        Post post = Post.builder().id(postId).name("Old Name").description("Description").build();
        Post updatedPost = Post.builder().id(postId).name(newName).description("Description").build();
        PostDTO postDTO = PostDTO.builder().id(postId).name(newName).description("Description").build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(updatedPost);
        when(postMapper.toItemDto(updatedPost)).thenReturn(postDTO);

        PostDTO result = postService.changeName(postId, newName);

        assertEquals(newName, result.getName());
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(1)).toItemDto(updatedPost);
    }

    @Test
    void update() {
        Long postId = 1L;
        PostRequestDTO postRequestDTO = PostRequestDTO.builder().name("Updated Name").description("Updated Description").build();
        Post post = Post.builder().id(postId).name("Sample Post").description("Sample Description").build();
        Post updatedPost = Post.builder().id(postId).name("Updated Name").description("Updated Description").build();
        PostDTO postDTO = PostDTO.builder().id(postId).name("Updated Name").description("Updated Description").build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(updatedPost);
        when(postMapper.toItemDto(updatedPost)).thenReturn(postDTO);

        PostDTO result = postService.update(postId, postRequestDTO);

        assertEquals("Updated Name", result.getName());
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(1)).toItemDto(updatedPost);
    }

    @Test
    void updatePhotoId() {
        Long postId = 1L;
        Photo photo = new Photo("filename.jpg", "image/jpeg", "some-image".getBytes());
        Post post = Post.builder().id(postId).name("Sample Post").description("Sample Description").build();
        Post updatedPost = Post.builder().id(postId).name("Sample Post").description("Sample Description").photo(photo).build();
        PostDTO postDTO = PostDTO.builder().id(postId).name("Sample Post").description("Sample Description").build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(updatedPost);
        when(postMapper.toItemDto(updatedPost)).thenReturn(postDTO);

        PostDTO result = postService.updatePhotoId(postId, photo);

        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(1)).toItemDto(updatedPost);
    }

    @Test
    void changeDescription() {
        Long postId = 1L;
        String newDescription = "New Description";
        Post post = Post.builder().id(postId).name("Sample Post").description("Old Description").build();
        Post updatedPost = Post.builder().id(postId).name("Sample Post").description("New Description").build();
        PostDTO postDTO = PostDTO.builder().id(postId).name("Sample Post").description("New Description").build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(updatedPost);
        when(postMapper.toItemDto(updatedPost)).thenReturn(postDTO);

        PostDTO result = postService.changeDescription(postId, newDescription);

        assertEquals(newDescription, result.getDescription());
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(1)).toItemDto(updatedPost);
    }

    @Test
    void getLikedPosts() {
        Long id = 1L;
        Post post = Post.builder().name("Sample Post").description("Sample Description").build();
        PostDTO postDTO = PostDTO.builder().name("Sample Post").description("Sample Description").build();

        when(postRepository.findAll()).thenReturn(List.of(post));
        when(postMapper.toItemDto(post)).thenReturn(postDTO);
        when(postRepository.findAllByUserId(id)).thenReturn(List.of(postDTO));

        List<PostDTO> result = postService.getLikedPosts(id);

        assertEquals(1, result.size());
        assertEquals("Sample Post", result.get(0).getName());
    }

}