package com.example.hw1.service.dto;

import com.example.hw1.repository.model.Operation;
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

    private String content;
    private Operation.OperationType type;
    private final Instant timestamp = Instant.now();
}
