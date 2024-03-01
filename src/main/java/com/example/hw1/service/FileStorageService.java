package com.example.hw1.service;

import com.example.hw1.repository.exception.ObjectReadException;
import com.example.hw1.repository.exception.ObjectWriteException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    @Data
    @AllArgsConstructor
    class FileSaveResult {
        private String link;
    }

    FileSaveResult saveFile(MultipartFile file) throws IOException, ObjectWriteException;

    InputStream readFile(String filename) throws ObjectReadException;

    void deleteFile(String filename) throws ObjectWriteException;
}
