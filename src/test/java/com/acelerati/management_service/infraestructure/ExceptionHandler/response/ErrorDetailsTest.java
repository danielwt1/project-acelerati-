package com.acelerati.management_service.infraestructure.ExceptionHandler.response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ErrorDetailsTest {
    private static ErrorDetails errorDetails = null;
    @BeforeAll
    static void setUp() {
        errorDetails = new ErrorDetails(LocalDateTime.of(2023,02,22,6,22),"Error","/ruta");
    }

    @Test
    void getTimestamp() {
        assertEquals(LocalDateTime.of(2023,02,22,6,22),errorDetails.getTimestamp());

    }

    @Test
    void getMessage() {
        assertEquals("Error",errorDetails.getMessage());
    }

    @Test
    void getPath() {
        assertEquals("/ruta",errorDetails.getPath());
    }
}