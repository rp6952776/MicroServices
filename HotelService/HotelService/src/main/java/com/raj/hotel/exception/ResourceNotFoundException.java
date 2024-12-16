package com.raj.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Not Found!!");

    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
