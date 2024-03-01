package com.example.hw1.service.mapper;

import com.example.hw1.repository.model.Operation;
import com.example.hw1.service.dto.OperationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    Operation operationDtoToOperation(OperationDto dto);

    List<OperationDto> operationListToOperationDtoList(List<Operation> operations);
}
