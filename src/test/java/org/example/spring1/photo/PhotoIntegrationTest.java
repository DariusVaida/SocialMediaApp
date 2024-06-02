package org.example.spring1.photo;

import org.example.project.core.SpringIntegrationBaseTest;
import org.example.spring1.post.PostRepository;
import org.example.spring1.post.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhotoIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private PhotoController photoController;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PhotoService photoService;

    @BeforeEach
    void tearDown() {
        photoRepository.deleteAll();
    }



    @Test
    void delete() {
        Photo photo = new Photo("filename.jpg", "image/jpeg", "some-image".getBytes());
        photoRepository.save(photo);

        photoService.delete(photo.getId());

        assertEquals(0, photoRepository.findAll().size());
    }
}
