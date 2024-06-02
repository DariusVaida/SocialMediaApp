package org.example.spring1.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.example.spring1.UrlMapping.PHOTO;
import static org.example.spring1.UrlMapping.UPLOAD_FILE;


@RequestMapping(PHOTO)
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping(UPLOAD_FILE)
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file, @RequestParam("postId") Long postId) {

        return photoService.handleFile(file, postId);
    }
}