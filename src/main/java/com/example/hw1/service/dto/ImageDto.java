package com.example.hw1.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class ImageDto implements Serializable {

    private long id;
    private String originalName;
    private long size;
    private String link;
}
