package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {
    @Mock
    private InventorySpringService inventorySpringService;

    @InjectMocks
    private InventoryController inventoryRestController;

    @Test
    void addInventory_shouldReturnCreatedResponse() throws MethodArgumentNotValidException {
        // Given
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO("producto",5000L, BigDecimal.valueOf(5000), 1L,1L), new InventoryDTO("producto",5000L,BigDecimal.valueOf(5000), 1L,1L));
         // When
        ResponseEntity<Void> responseEntity = inventoryRestController.addInventory(inventoryDTOList);
        // Then
        verify(inventorySpringService).addInventory(inventoryDTOList);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateSalePrice_shouldReturnOkStatus() {
        // Given
        InventoryUpdateRequestDTO inventoryDTO = new InventoryUpdateRequestDTO("producto",5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L,1L);
        // When
        ResponseEntity<Void> responseEntity = inventoryRestController.updateSalePrice(inventoryDTO);
        // Then
        verify(inventorySpringService).updateProductSalePrice(inventoryDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}