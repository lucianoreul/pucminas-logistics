package com.ms.logistics.user.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

    private final HttpStatus status;

    public BusinessException(String message) {
        this(message, null);
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

