package com.example.demo.domain.exception;

public class InvalidInputDataException extends RuntimeException {
    public InvalidInputDataException(String msg) {
        super(msg);
    }
}
