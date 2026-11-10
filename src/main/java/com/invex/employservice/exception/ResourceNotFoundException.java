package com.invex.employservice.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
