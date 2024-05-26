package org.example.spring1.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) {

        Photo dbFile = photoService.storeFile(file);


        return ResponseEntity.ok()
                .body(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/photo/downloadFile/")
                        .toUriString());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        Photo photo = photoService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .body(photo.getData());
    }
}