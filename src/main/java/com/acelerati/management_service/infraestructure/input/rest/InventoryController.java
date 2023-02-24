package com.acelerati.management_service.infraestructure.input.rest;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RequestMapping("/api/v1/inventory/")
@RestController
@Validated
public class InventoryController {
    private final InventorySpringService inventorySpringService;

    public InventoryController(InventorySpringService inventorySpringService) {
        this.inventorySpringService = inventorySpringService;
    }
    @Operation(summary = "ADD items to inventory")
    @PostMapping("/")
    public ResponseEntity<Void>addInventory(@RequestBody @NotEmpty(message = "List must not empty") List<@Valid InventoryDTO> inventoryDTO) throws MethodArgumentNotValidException {
        this.inventorySpringService.addInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
