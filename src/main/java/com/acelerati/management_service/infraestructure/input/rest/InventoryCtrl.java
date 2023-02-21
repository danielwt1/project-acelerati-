package com.acelerati.management_service.infraestructure.input.rest;


import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
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


@RequestMapping("/api/inventory/")
@RestController
@Validated
public class InventoryCtrl {
    private final InventorySpringService inventorySpringService;

    public InventoryCtrl(InventorySpringService inventorySpringService) {
        this.inventorySpringService = inventorySpringService;
    }

    @PostMapping("/v1/addInventory")
    public ResponseEntity<Void>addInventory(@RequestBody @NotEmpty(message = "La lista de productos no puede estar vacia") List<@Valid InventoryDTO> inventoryDTO) throws MethodArgumentNotValidException {
        this.inventorySpringService.addInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
