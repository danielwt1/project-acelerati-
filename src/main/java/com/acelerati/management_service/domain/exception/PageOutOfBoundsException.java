package com.acelerati.management_service.domain.exception;

public class PageOutOfBoundsException extends RuntimeException {
    public PageOutOfBoundsException(String message) {
        super(message);
    }
}
