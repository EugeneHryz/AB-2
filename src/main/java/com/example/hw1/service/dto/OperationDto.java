package com.example.hw1.service.dto;

import com.example.hw1.repository.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationDto {

    private String id;
    private String content;
    private Long initiatorId;
    private Instant timestamp;
    private OperationType type;
}
