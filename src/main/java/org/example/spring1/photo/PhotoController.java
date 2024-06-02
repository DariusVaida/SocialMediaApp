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

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file, @RequestParam("postId") Long postId) {

        return photoService.handleFile(file, postId);
    }
}