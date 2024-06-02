package org.example.spring1.photo;

import org.example.project.core.SpringControllerBaseTest;
import org.example.spring1.post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PhotoControllerTest extends SpringControllerBaseTest {

    @InjectMocks
    private PhotoController photoController;

    @Mock
    private PhotoService photoService;

    @Mock
    private PostService postService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        photoController = new PhotoController(photoService);
        mvc = buildForController(photoController);
    }

    @Test
    void uploadFile() {
        MockMultipartFile file = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "some-image".getBytes());
        Long postId = 1L;

        when(photoService.handleFile(file, postId)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = photoController.uploadFile(file, postId);

        assertEquals(200, response.getStatusCodeValue());
        verify(photoService, times(1)).handleFile(file, postId);
    }
}
