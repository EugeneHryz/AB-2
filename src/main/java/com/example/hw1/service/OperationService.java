package com.example.hw1.service;

import com.example.hw1.repository.OperationRepository;
import com.example.hw1.service.dto.OperationDto;
import com.example.hw1.service.mapper.OperationMapper;
import com.mongodb.client.model.changestream.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;

    public void logOperation(OperationDto operation) {
        operationRepository.save(mapper.operationDtoToOperation(operation));
    }

    public List<OperationDto> getOperationsByType(OperationType type) {
        return mapper.operationListToOperationDtoList(operationRepository.findAllByType(type));
    }
}
