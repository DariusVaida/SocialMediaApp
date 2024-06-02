package org.example.spring1.exceptions;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }
}
