package com.acelerati.management_service.application.dto.request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class InventoryDTOTest {
    private static InventoryDTO invetoryDTO = null;

    @BeforeAll
    static void setUp() {
        invetoryDTO = new InventoryDTO("producto",5000L,BigDecimal.valueOf(5000), 1L,1L);
    }

    @Test
    void getName() {
        assertEquals("producto", invetoryDTO.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000L, invetoryDTO.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000), invetoryDTO.getUnitPrice());
    }

    @Test
    void getIdProduct() {
        assertEquals(1L , invetoryDTO.getIdProduct());
    }

    @Test
    void getIdSupplier() {
        assertEquals(1L, invetoryDTO.getIdSupplier());
    }
}