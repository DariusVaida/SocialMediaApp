package org.example.spring1.photo;

import org.example.project.core.SpringUnitBaseTest;
import org.example.spring1.exceptions.FileStorageException;
import org.example.spring1.post.PostRepository;
import org.example.spring1.post.PostService;
import org.example.spring1.post.model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhotoServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private PhotoService photoService;

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    void storeFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "some-image".getBytes());
        Photo photo = new Photo("filename.jpg", "image/jpeg", file.getBytes());

        when(photoRepository.save(any(Photo.class))).thenReturn(photo);

        Photo storedPhoto = photoService.storeFile(file);

        assertNotNull(storedPhoto);
        assertEquals("filename.jpg", storedPhoto.getName());
        verify(photoRepository, times(1)).save(any(Photo.class));
    }

    @Test
    void storeFile_invalidPathSequence() {
        MockMultipartFile file = new MockMultipartFile("image", "../filename.jpg", "image/jpeg", "some-image".getBytes());

        assertThrows(FileStorageException.class, () -> photoService.storeFile(file));
    }

    @Test
    void getFile() {
        String fileId = "1";
        Photo photo = new Photo("filename.jpg", "image/jpeg", "some-image".getBytes());

        when(photoRepository.findById(fileId)).thenReturn(Optional.of(photo));

        Photo result = photoService.getFile(fileId);

        assertEquals(photo, result);
    }

    @Test
    void getFile_notFound() {
        String fileId = "1";

        when(photoRepository.findById(fileId)).thenReturn(Optional.empty());

        assertThrows(FileStorageException.class, () -> photoService.getFile(fileId));
    }

    @Test
    void getAllPhotos() {
        Photo photo = new Photo("filename.jpg", "image/jpeg", "some-image".getBytes());

        when(photoRepository.findAll()).thenReturn(List.of(photo));

        List<Photo> photos = photoService.getAllPhotos();

        assertEquals(1, photos.size());
    }

    @Test
    void setPost() {
        Long id = 1L;
        Long postId = 1L;
        Photo photo = new Photo("filename.jpg", "image/jpeg", "some-image".getBytes());

        when(photoRepository.findById(id.toString())).thenReturn(Optional.of(photo));

        photoService.setPost(id, postId);

        verify(photoRepository, times(1)).save(photo);
    }

    @Test
    void delete() {
        Long id = 1L;

        photoService.delete(id);

        verify(photoRepository, times(1)).deleteById(id.toString());
    }


    @Test
    void deleteByPostId() {
        Long postId = 1L;

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        photoService.deleteByPostId(postId);

        verify(postRepository, times(1)).findById(postId);
        verify(photoRepository, times(0)).deleteById(anyString());
    }

    @Test
    void handleFile() {
        // can't test
    }
}
