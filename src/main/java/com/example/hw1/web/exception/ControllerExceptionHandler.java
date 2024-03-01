package com.example.hw1.web.exception;

import com.example.hw1.service.exception.EntityNotFoundException;
import com.example.hw1.service.exception.UnprocessableEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(createBasicResponseMessage(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UnprocessableEntityException.class })
    public ResponseEntity<?> handleUnprocessableEntityException(UnprocessableEntityException e) {
        return new ResponseEntity<>(createBasicResponseMessage(e), HttpStatus.CONFLICT);
    }

//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<?> handleAnyException(Exception e) {
//        return new ResponseEntity<>(createBasicResponseMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleRequestObjectValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createBasicResponseMessage(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        return body;
    }
}
