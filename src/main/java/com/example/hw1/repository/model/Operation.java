package com.example.hw1.repository.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document
public class Operation {

    @Id
    private String id;
    private String content;
    private Instant timestamp;
    private OperationType type;

    public enum OperationType {
        READ, WRITE
    }
}
