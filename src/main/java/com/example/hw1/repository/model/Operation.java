package com.example.hw1.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Operation {

    @Id
    private String id;
    private String content;

    // id of a user who performed the operation
    private Long initiatorId;

    private Instant timestamp;
    private OperationType type;
}
