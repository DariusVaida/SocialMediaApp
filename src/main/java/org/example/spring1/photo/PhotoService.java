package org.example.spring1.photo;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
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
}
