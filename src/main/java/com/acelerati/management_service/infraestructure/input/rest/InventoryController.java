package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.infraestructure.ExceptionHandler.response.ErrorDetails;
import com.acelerati.management_service.infraestructure.exception.UnavailableMicroserviceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class InventoryController {
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
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @GetMapping("/sale")
    public ResponseEntity<List<ProductsForSaleDTO>> getAllProductsForSale(@RequestHeader(value = "user") String user,
                                                                          @RequestParam(required = false, defaultValue = "", name = "name") String name,
                                                                          @RequestParam(required = false, defaultValue = "", name = "brandName") String brandName,
                                                                          @RequestParam(required = false, defaultValue = "", name = "nombreCategoria") String nameCategory,
                                                                          @RequestParam(required = false, defaultValue = "1", name = "page") Integer page,
                                                                          @RequestParam(required = false, defaultValue = "10", name = "elementPerPage") Integer elementPerPage) throws UnavailableMicroserviceException {
        List<ProductsForSaleDTO> responseData = this.inventorySpringService.getAllProductForSale(name, brandName, nameCategory, page, elementPerPage);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @Operation(summary = "Add items to the inventory", responses = {
            @ApiResponse(responseCode = "200", description = "Add items to the inventory"),
            @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PostMapping("/")
    @PreAuthorize("@authService.checkEmployeeRole(@authService.rolesContext)")
    public ResponseEntity<Void> addInventory(@RequestHeader(value = "user") String user,
                                             @RequestBody @NotEmpty(message = "The product list must not be empty") List<@Valid InventoryDTO> inventoryDTO) {
        this.inventorySpringService.addInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Search inventory items by several criteria and serves the result paginated.", responses = {
            @ApiResponse(responseCode = "200", description = "The search was made successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FilterInventoryResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)))
    }, parameters = {
            @Parameter()
    })
    @GetMapping(path = {"/"})
    @PreAuthorize("@authService.checkEmployeeRole(@authService.rolesContext)")
    public ResponseEntity<FilterInventoryResponseDTO> getInventoriesBy(@RequestHeader(value = "user") String user,
                                                                       @Valid InventorySearchCriteriaDTO searchCriteria, @Valid PaginationDTO paginationDTO) throws UnavailableMicroserviceException {
        FilterInventoryResponseDTO filterInventoryResponse = inventorySpringService.getInventoriesBy(searchCriteria, paginationDTO);
        return new ResponseEntity<>(filterInventoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Sale Price to product")
    @PutMapping("/")
    public  ResponseEntity<Void>updateSalePrice(@RequestHeader(value = "user")String user,
                                                @RequestBody @Valid InventoryUpdateRequestDTO inventoryDTO){
        this.inventorySpringService.updateProductSalePrice(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
