package com.example.hw1.repository.exception;

public class ObjectWriteException extends Exception {

    public ObjectWriteException() {
    }

    public ObjectWriteException(String message) {
        super(message);
    }

    public ObjectWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectWriteException(Throwable cause) {
        super(cause);
    }
}
