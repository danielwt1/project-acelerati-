package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationRequestDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.infraestructure.exceptionhandler.response.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RequestMapping("/api/v1/inventory")
@RestController
@Validated
@Tag(name = "Inventory", description = "Represents the functional contract for managing products in stock")
public class InventoryController {
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    private final InventorySpringService inventorySpringService;

    public InventoryController(InventorySpringService inventorySpringService) {
        this.inventorySpringService = inventorySpringService;
    }

    @Operation(summary = "get all products for sale ", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductsForSaleDTO[].class))),
            @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "403", description = "UNNECESSARY PERMISSIONS",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sale")
    @PreAuthorize("@authService.checkClientRole(@authService.rolesContext)")
    public ResponseEntity<List<ProductsForSaleDTO>> getAllProductsForSale(@RequestHeader(value = "user") String user,
                                                                          @RequestParam(required = false, defaultValue = "", name = "name") String name,
                                                                          @RequestParam(required = false, defaultValue = "", name = "brandName") String brandName,
                                                                          @RequestParam(required = false, defaultValue = "", name = "nombreCategoria") String nameCategory,
                                                                          @RequestParam(required = false, defaultValue = "1", name = "page") Integer page,
                                                                          @RequestParam(required = false, defaultValue = "10", name = "elementPerPage") Integer elementPerPage) {
        List<ProductsForSaleDTO> responseData = this.inventorySpringService.getAllProductForSale(name, brandName, nameCategory, page, elementPerPage);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @Operation(summary = "Add items to the inventory", responses = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json")),

            @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PostMapping
    @PreAuthorize("@authService.checkEmployeeRole(@authService.rolesContext)")
    public ResponseEntity<Void> addInventory(@RequestHeader(value = "user") String user,
                                             @RequestBody @NotEmpty(message = "The product list must not be empty") List<@Valid InventoryDTO> inventoryDTO) {
        this.inventorySpringService.addInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Searches products from the Inventory by Sale Price range, Category ID and Brand ID. It gets the result paginated given the Page Number and the Page Size",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The search was made successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = FilterInventoryResponseDTO.class))),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "The user does not have permissions to execute this feature or, the login process failed",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            },
            parameters = {
                    @Parameter(name = "searchCriteria", schema = @Schema(type = "object",
                            implementation = InventorySearchCriteriaDTO.class),
                            description = "The properties the Inventory will be searched by"),
                    @Parameter(name = "pagination", schema = @Schema(type = "object",
                            implementation = PaginationRequestDTO.class),
                            description = "Describes how the search result will be paginated"),
                    @Parameter(name = "user", schema = @Schema(type = "integer"),
                            description = "User identification for authentication purposes")
            }
    )
    @GetMapping
    @PreAuthorize("@authService.checkEmployeeRole(@authService.rolesContext)")
    public ResponseEntity<FilterInventoryResponseDTO> getInventoriesBy(@RequestHeader(value = "user") String user,
                                                                       @Valid InventorySearchCriteriaDTO searchCriteria, @Valid PaginationRequestDTO pagination) {
        logger.debug("Search criteria will be {}", searchCriteria);
        logger.debug("Pagination requested as {}", pagination);
        FilterInventoryResponseDTO filterInventoryResponse = inventorySpringService.getInventoriesBy(searchCriteria, pagination);
        return new ResponseEntity<>(filterInventoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Sale Price to product")
    @PutMapping
    @PreAuthorize("@authService.checkEmployeeRole(@authService.rolesContext)")
    public ResponseEntity<Void> updateSalePrice(@RequestHeader(value = "user") String user,
                                                @RequestBody @Valid InventoryUpdateRequestDTO inventoryDTO) {
        this.inventorySpringService.updateProductSalePrice(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Checks if the application is responding to any requests.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The application is responding to HTTP requests",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "string", example = "Hello from Excalibur!"))),
                    @ApiResponse(responseCode = "503",
                            description = "The application is down or overloaded, it cannot respond any request",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "string", example = "Service temporarily unavailable")))
            })
    @GetMapping("/health")
    public ResponseEntity<String> getHealthStatus() {
        return ResponseEntity.ok("Hello from Excalibur!");
    }
}
