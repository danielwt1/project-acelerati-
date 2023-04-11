package com.acelerati.management_service.infraestructure.exception;

public class UnavailableMicroserviceException extends Exception {
    public UnavailableMicroserviceException(String message) {
        super(message);
    }
}
