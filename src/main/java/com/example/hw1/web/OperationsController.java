package com.example.hw1.web;

import com.example.hw1.repository.model.OperationType;
import com.example.hw1.service.OperationService;
import com.example.hw1.service.dto.OperationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OperationsController {

    private final OperationService operationService;

    @QueryMapping
    public List<OperationDto> getOperationsByType(@Argument OperationType type) {
        return operationService.getOperationsByType(type);
    }
}
