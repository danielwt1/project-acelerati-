package com.acelerati.management_service.infraestructure.ExceptionHandler.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class ErrorDetails {
    private final LocalDateTime timestamp;
    private final String message;
    private final String path;


}
