package com.acelerati.management_service.infraestructure.exceptionhandler.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "Wrapper object to specify that an error has been occurred in the application.")
public class ErrorDetails {
    @Schema(description = "The date/time when the occurred error.")
    private final LocalDateTime timestamp;

    @Schema(description = "The message of the occurred error.", example = "An error has occurred!")
    private final String message;

    @Schema(description = "The path of the resource which raised the error.", example = "/api/v1/inventory")
    private final String path;
}
