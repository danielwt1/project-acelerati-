package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class InventoryResponseDTOTest {
    private static  InventoryResponseDTO inventory = null;
    @BeforeAll
    static void setUp() {
        inventory = new InventoryResponseDTO(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
    }
    @Test
    void getId() {
        assertEquals(1L,inventory.getIdInventory());
    }

    @Test
    void getName() {
        assertEquals("producto",inventory.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000L,inventory.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000),inventory.getUnitPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals(BigDecimal.valueOf(6000),inventory.getSalePrice());

    }

    @Test
    void getIdProduct() {
        assertEquals(1L,inventory.getIdProduct());

    }

    @Test
    void getIdSupplier() {
        assertEquals(1L,inventory.getIdSupplier());
    }
}