package com.acelerati.management_service.infraestructure.exceptionhandler;

import com.acelerati.management_service.infraestructure.exceptionhandler.response.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {
    GlobalExceptionHandler globalExceptionHandler;
    ResponseEntity<ErrorDetails> response;
    ErrorDetails body ;
    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        body = new ErrorDetails(LocalDateTime.of(2023,02,22,10,22),"Error","/path");
    }

    @Test
    void whenThrowExceptionNotControlledThenHandleAllExceptions() {
        WebRequest webrequest = mock(WebRequest.class);
        response = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        when(webrequest.getDescription(false)).thenReturn("/path");
        ResponseEntity<ErrorDetails> responseReal = this.globalExceptionHandler.handleAllExceptions(new Exception("Error"),webrequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,responseReal.getStatusCode());
    }

    @Test
    void whenThrowConstraintViolationExceptionHandleAll() {
        WebRequest webrequest = mock(WebRequest.class);
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        when(webrequest.getDescription(false)).thenReturn("/path");
        ResponseEntity<ErrorDetails> responseReal = this.globalExceptionHandler.handleConstraintViolationException(exception,webrequest);
        assertEquals(HttpStatus.BAD_REQUEST,responseReal.getStatusCode());
    }


}