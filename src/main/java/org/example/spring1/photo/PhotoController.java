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


@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final PostService postService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(PhotoRequestDTO dto) {

        MultipartFile file = dto.getImage();
        Photo dbFile = photoService.storeFile(file);
        Long postId = dto.getPostId();
        photoService.setPost(dbFile.getId(), postId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        Photo photo = photoService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .body(photo.getData());
    }
}