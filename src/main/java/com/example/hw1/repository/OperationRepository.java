package com.example.hw1.repository;

import com.example.hw1.repository.model.Operation;
import com.mongodb.client.model.changestream.OperationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findAllByType(OperationType type);
}
