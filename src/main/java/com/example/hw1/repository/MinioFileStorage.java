package com.example.hw1.repository;

import com.example.hw1.repository.exception.ObjectReadException;
import com.example.hw1.repository.exception.ObjectWriteException;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class MinioFileStorage {

    @Value("${minio.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;

    public ObjectWriteResponse saveObject(String name, InputStream object, Long size) throws ObjectWriteException {
        try {
            return minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .stream(object, size, -1).build());
        } catch (Exception e) {
            throw new ObjectWriteException(e);
        }
    }

    public boolean doesObjectExist(String name) {
        try {
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name).build());
            return true;
        } catch (ErrorResponseException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream readObject(String name) throws ObjectReadException {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name).build());
        } catch (Exception e) {
            throw new ObjectReadException(e);
        }
    }

    public void deleteObject(String name) throws ObjectWriteException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .build());
        } catch (Exception e) {
            throw new ObjectWriteException(e);
        }
    }
}
