package org.example.spring1.photo;

import lombok.RequiredArgsConstructor;
import org.example.spring1.photo.dto.PhotoRequestDTO;
import org.example.spring1.post.PostService;
import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final PostService postService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file, @RequestParam("postId") Long postId) {

        Photo dbFile = photoService.storeFile(file);
        photoService.setPost(dbFile.getId(), postId);

        postService.updatePhotoId(postId, dbFile);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<byte[]> getPhotos() {

        List<Photo> photos = photoService.getAllPhotos();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(photos.get(0).getData());
    }
}