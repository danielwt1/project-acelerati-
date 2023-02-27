package com.acelerati.management_service.infraestructure.input.rest;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Add items to the inventory")
    @PostMapping("/")
    public ResponseEntity<Void>addInventory(@RequestBody @NotEmpty(message = "The product list must not be empty") List<@Valid InventoryDTO> inventoryDTO) throws MethodArgumentNotValidException {
        this.inventorySpringService.addInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Search inventory items by several criteria and serves the result paginated.")
    @GetMapping(path = {"/"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilterInventoryResponseDTO> getInventoriesBy(InventorySearchCriteriaDTO searchCriteria, PaginationDTO paginationDTO) {
        FilterInventoryResponseDTO filterInventoryResponse = inventorySpringService.getInventoriesBy(searchCriteria, paginationDTO);
        return new ResponseEntity<>(filterInventoryResponse, HttpStatus.OK);
    }
}
