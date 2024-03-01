package com.example.hw1.web;

import com.example.hw1.service.ImageService;
import com.example.hw1.service.dto.ImageDto;
import com.example.hw1.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ImageDto addUserImage(@RequestPart("image") MultipartFile image,
                                 @RequestPart("user") UserDto userDto) {
        return imageService.addUserImage(userDto.getId(), image);
    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Resource getImage(@RequestParam String link) {
        return imageService.getImageByLink(link);
    }
}
