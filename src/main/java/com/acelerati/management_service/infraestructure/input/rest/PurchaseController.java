package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.handler.PurchaseSpringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase")
@Schema(description = "This controller handles all the operations regarding purchasing")
@Tag(name = "Purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseSpringService purchaseSpringService;

    @Operation(summary = "Submits a purchase request for the cart attached to the provided User ID and then drops it.",
    parameters = @Parameter(description = "The User ID who is requesting the purchase", example = "12345"),
    responses = {
            @ApiResponse(responseCode = "201", description = "The purchase request was created successfully and sent to evaluation"),
            @ApiResponse(responseCode = "403", description = "The user does not have rights to execute this feature"),
            @ApiResponse(responseCode = "401", description = "The user authentication process failed"),
            @ApiResponse(responseCode = "500", description = "An unhandled internal error occurred")
    })
    @PostMapping("")
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    public ResponseEntity<String> submitPurchaseRequest(@RequestHeader String user) {
        return new ResponseEntity<>(purchaseSpringService.createPurchaseRequest(Long.parseLong(user)),
                HttpStatus.CREATED);
    }
}
