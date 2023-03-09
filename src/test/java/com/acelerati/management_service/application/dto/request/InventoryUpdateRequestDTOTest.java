package com.acelerati.management_service.application.dto.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryUpdateRequestDTOTest {
    private static InventoryUpdateRequestDTO inventoryUpdateRequestDTO = null;

    @BeforeAll
    static void setUp() {
        inventoryUpdateRequestDTO = new InventoryUpdateRequestDTO("producto", 5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(5000), 1L, 1L);
    }

    @Test
    void getName() {
        assertEquals("producto", inventoryUpdateRequestDTO.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000L, inventoryUpdateRequestDTO.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000), inventoryUpdateRequestDTO.getUnitPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals(BigDecimal.valueOf(5000), inventoryUpdateRequestDTO.getSalePrice());
    }

    @Test
    void getIdProduct() {
        assertEquals(1L , inventoryUpdateRequestDTO.getIdProduct());
    }

    @Test
    void getIdSupplier() {
        assertEquals(1L, inventoryUpdateRequestDTO.getIdSupplier());
    }
}