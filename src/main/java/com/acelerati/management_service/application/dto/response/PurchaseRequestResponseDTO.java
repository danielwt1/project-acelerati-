package com.acelerati.management_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(name = "PurchaseRequestResponse", description = "Represents the result of making a purchase request.")
public class PurchaseRequestResponseDTO {
    @Schema(description = "Tells the client if the purchase request succeed or failed.")
    private String message;
}
