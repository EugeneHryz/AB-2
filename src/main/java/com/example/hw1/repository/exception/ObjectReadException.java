package com.example.hw1.repository.exception;

public class ObjectReadException extends Exception {

    public ObjectReadException() {
    }

    public ObjectReadException(String message) {
        super(message);
    }

    public ObjectReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectReadException(Throwable cause) {
        super(cause);
    }
}
