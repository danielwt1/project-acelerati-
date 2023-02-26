package com.acelerati.management_service.infraestructure.ExceptionHandler;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
