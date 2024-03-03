package com.example.hw1.service;

import com.example.hw1.repository.ImageRepository;
import com.example.hw1.repository.UserRepository;
import com.example.hw1.repository.exception.ObjectReadException;
import com.example.hw1.repository.exception.ObjectWriteException;
import com.example.hw1.repository.model.Image;
import com.example.hw1.repository.model.OperationType;
import com.example.hw1.repository.model.User;
import com.example.hw1.service.dto.ImageDto;
import com.example.hw1.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final OperationService operationService;

    private final ImageMapper imageMapper;

    @CacheEvict(value = "ImageService::getAllByUser", key="#userId")
    @Cacheable(value = "ImageService::addUserImage", key = "#image.originalFilename")
    @Transactional
    public ImageDto addUserImage(Long userId, MultipartFile image) {
        try {
            FileStorageService.FileSaveResult result = fileStorageService.saveFile(image);

            User user = userRepository.getReferenceById(userId);
            Image imageMetadata = Image.builder()
                    .originalName(image.getOriginalFilename())
                    .size(image.getSize())
                    .link(result.getLink())
                    .user(user).build();
            imageRepository.save(imageMetadata);

            operationService.logOperation(
                    String.format("Uploading new image: %s", imageMetadata),
                    OperationType.ADD_USER_IMAGE);

            return imageMapper.imageToImageDto(imageMetadata);
        } catch (ObjectWriteException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource getImageByLink(String link) {
        try {
            InputStreamResource resource = new InputStreamResource(fileStorageService.readFile(link));
            return resource;
        } catch (ObjectReadException e) {
            throw new RuntimeException(e);
        } finally {
            operationService.logOperation(
                    String.format("Downloading user's image with link: %s", link),
                    OperationType.DOWNLOAD_IMAGE);
        }
    }

    @Cacheable(value = "ImageService::getAllByUser", key = "#userId")
    public List<ImageDto> getAllByUser(Long userId) {

        operationService.logOperation(
                String.format("Getting images metadata for user: %d", userId),
                OperationType.GET_USER_IMAGES_METADATA);

        return imageRepository.findByUserId(userId).stream()
                .map(imageMapper::imageToImageDto)
                .toList();
    }
}
