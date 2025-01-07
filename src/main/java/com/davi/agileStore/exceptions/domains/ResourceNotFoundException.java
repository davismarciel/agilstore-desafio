package com.davi.agileStore.exceptions.domains;

public class ResourceNotFoundException extends RuntimeException {
    public String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
