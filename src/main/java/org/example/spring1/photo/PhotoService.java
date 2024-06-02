package org.example.spring1.photo;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exceptions.FileStorageException;
import org.example.spring1.post.PostRepository;
import org.example.spring1.post.PostService;
import org.example.spring1.post.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    public Photo storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }


            Photo dbFile = new Photo(fileName, file.getContentType(), file.getBytes());

            return photoRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public Photo getFile(String fileId) {
        return photoRepository.findById(fileId)
                .orElseThrow(() -> new FileStorageException("File not found with id " + fileId));
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public void setPost(Long id, Long postId) {


        Photo photo = photoRepository.findById(id.toString()).orElseThrow(() -> new FileStorageException("File not found with id " + id));
        photoRepository.save(photo);

    }

    public void delete(Long id) {

        photoRepository.deleteById(id.toString());
    }

    public ResponseEntity<?> handleFile(MultipartFile file, Long postId) {

        Photo dbFile = storeFile(file);

        setPost(dbFile.getId(), postId);

        postService.updatePhotoId(postId, dbFile);

        return ResponseEntity.ok().build();
    }

    public void deleteByPostId(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            Long photoId = post.get().getPhoto().getId();
            photoRepository.deleteById(photoId.toString());
        }
    }
}
