package com.acelerati.management_service.domain.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartEmptyException extends RuntimeException {
    public static final Logger LOGGER = LoggerFactory.getLogger(CartEmptyException.class);

    public CartEmptyException(String message) {
        super(message);
        LOGGER.debug("Throwing CartEmptyException: {}", message);
    }
}
