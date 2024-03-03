package com.example.hw1.service;

import com.example.hw1.repository.OperationRepository;
import com.example.hw1.repository.model.Operation;
import com.example.hw1.repository.model.OperationType;
import com.example.hw1.service.dto.OperationDto;
import com.example.hw1.service.mapper.OperationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;

    public void logOperation(Long userId, String content, OperationType opType) {
        Operation operation = Operation.builder()
                .initiatorId(userId)
                .content(content)
                .timestamp(Instant.now())
                .type(opType)
                .build();

        operationRepository.save(operation);
    }

    public void logOperation(String content, OperationType opType) {
        Operation operation = Operation.builder()
                .content(content)
                .timestamp(Instant.now())
                .type(opType)
                .build();

        operationRepository.save(operation);
    }

    public List<OperationDto> getOperationsByType(OperationType type) {
        List<Operation> operations = operationRepository.findAllByType(type);
        return mapper.operationListToOperationDtoList(operations);
    }
}
