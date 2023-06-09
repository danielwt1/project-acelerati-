package com.acelerati.management_service.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
        log.debug("Throwing CartNotFoundException: {}", message);
    }
}
