package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.response.CartDTO;
import com.acelerati.management_service.application.dto.response.PurchaseRequestResponseDTO;
import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import com.acelerati.management_service.infraestructure.exceptionhandler.response.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartSpringService cartSpringService;

    public CartController(CartSpringService cartSpringService) {
        this.cartSpringService = cartSpringService;
    }

    @Operation(summary = "Delete Product from cart ", responses = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "A business logic error occurred", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "UNNECESSARY PERMISSIONS",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/")
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    public ResponseEntity<Void> deleteProductFromCart(@RequestHeader(name = "user") String user, @RequestParam(value = "idProduct") Long idProduct) {
        this.cartSpringService.deleteProductCart(idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Find cart by userId", responses = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartDTO.class))),
            @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "403", description = "UNNECESSARY PERMISSIONS",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/")
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    public ResponseEntity<CartDTO>getCart(@RequestHeader(name = "user")String user
            ,@RequestParam(name = "page")Integer page
            ,@RequestParam(name = "elementPerPage")Integer elementPerPage){
        CartDTO response = this.cartSpringService.getCartByIdUser(page,elementPerPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Submits a purchase request, validates the stocks and notifies the user if the purchase can be done or not.",
    parameters = {
            @Parameter(name = "user", schema = @Schema(type = "integer"),
                    description = "User identification for authentication purposes")
    })
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    @DeleteMapping("/purchase")
    public ResponseEntity<PurchaseRequestResponseDTO> makePurchaseRequest(@RequestHeader(name = "user") String user) {
        return null;
    }

    @Operation(summary = "Add a product to the cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product added to the cart",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))
            )
    })
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    @PostMapping("/")
    public ResponseEntity<Void> addProductToCart(@RequestBody AddProductToCartDTO addProductToCartDTO){
        cartSpringService.addProductToCart(addProductToCartDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
