package com.example.s7testtask.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class IllegalRequestDataException extends ResponseStatusException {
    private final ErrorAttributeOptions options;

    public IllegalRequestDataException(HttpStatus status, String message, ErrorAttributeOptions options) {
        super(status, message);
        this.options = options;
    }


    public IllegalRequestDataException(String msg) {
        this(HttpStatus.UNPROCESSABLE_ENTITY, msg, ErrorAttributeOptions.of(MESSAGE));
    }

    @Override
    public String getMessage() {
        return getReason();
    }

    public ErrorAttributeOptions getOptions() {
        return options;
    }
}
