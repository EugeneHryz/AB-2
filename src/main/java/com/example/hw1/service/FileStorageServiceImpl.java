package com.example.hw1.service;

import com.example.hw1.repository.MinioFileStorage;
import com.example.hw1.repository.exception.ObjectReadException;
import com.example.hw1.repository.exception.ObjectWriteException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final MinioFileStorage fileStorage;
    
    /**
     * Save file in the Minio storage with file extension as prefix
     * @param file file to save
     * @return generated file name
     * @throws IOException
     */
    public FileSaveResult saveFile(MultipartFile file) throws IOException, ObjectWriteException {

        String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        String generatedFileName = String.format("%s/%s", fileExt, UUID.randomUUID());

        try (InputStream fileInputStream = file.getInputStream()) {
            fileStorage.saveObject(generatedFileName, fileInputStream, file.getSize());
        }
        return new FileSaveResult(generatedFileName);
    }

    @Override
    public InputStream readFile(String filename) throws ObjectReadException {
        return fileStorage.readObject(filename);
    }

    @Override
    public void deleteFile(String filename) throws ObjectWriteException {
        fileStorage.deleteObject(filename);
    }
}
