package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartInventoryDTOTest {
    CartInventoryDTO cartInventoryDTO;
    InventoryResponseDTO inventoryResponseDTO;

    @BeforeEach
    void setUp(){
        this.inventoryResponseDTO = new InventoryResponseDTO(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        this.cartInventoryDTO = new CartInventoryDTO(1L,inventoryResponseDTO);
    }

    @Test
    void getId() {
        assertEquals(1L,this.cartInventoryDTO.getId());
    }

    @Test
    void getInventory() {
        assertNotNull(this.cartInventoryDTO.getInventory());
    }
}