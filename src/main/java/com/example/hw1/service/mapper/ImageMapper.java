package com.example.hw1.service.mapper;

import com.example.hw1.repository.model.Image;
import com.example.hw1.service.dto.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto imageToImageDto(Image imageModel);
}
