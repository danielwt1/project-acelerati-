package com.acelerati.management_service.domain.Exception;

public class DomainNameIsEmptyException extends RuntimeException {
    public DomainNameIsEmptyException(String message) {
        super(message);
    }
}
