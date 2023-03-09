package com.acelerati.management_service.domain.model;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class InventoryModelTest {
    InventoryModel inventoryModel = null;
    @BeforeEach
    void setUp() {
        inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
    }

    @Test
    void getId() {
        assertEquals(1L,inventoryModel.getId());
    }

    @Test
    void setId() {
        InventoryEntity entity = new InventoryEntity();
        entity.setId(2L);
        assertNotNull(entity.getId());
        assertEquals(2L,entity.getId());
    }

    @Test
    void getName() {
        assertEquals("producto",inventoryModel.getName());
    }

    @Test
    void setName() {
        InventoryEntity entity = new InventoryEntity();
        entity.setName("producto2");
        assertFalse(entity.getName().isEmpty());
        assertEquals("producto2",entity.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000,inventoryModel.getStock());
    }

    @Test
    void setStock() {
        InventoryEntity entity = new InventoryEntity();
        entity.setStock(20L);
        assertNotNull(entity.getStock());
        assertEquals(20L,entity.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000),inventoryModel.getUnitPrice());
    }

    @Test
    void setUnitPrice() {
        InventoryEntity entity = new InventoryEntity();
        entity.setUnitPrice(BigDecimal.valueOf(6000));
        assertNotNull(entity.getUnitPrice());
        assertEquals(BigDecimal.valueOf(6000),entity.getUnitPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals(BigDecimal.valueOf(6000),inventoryModel.getSalePrice());
    }

    @Test
    void setSalePrice() {
        InventoryEntity entity = new InventoryEntity();
        entity.setSalePrice(BigDecimal.valueOf(7000));
        assertNotNull(entity.getSalePrice());
        assertEquals(BigDecimal.valueOf(7000),entity.getSalePrice());
    }

    @Test
    void getIdProduct() {
        assertEquals(1L,inventoryModel.getIdProduct());
    }

    @Test
    void setIdProduct() {
        InventoryEntity entity = new InventoryEntity();
        entity.setIdProduct(2L);
        assertNotNull(entity.getIdProduct());
        assertEquals(2L,entity.getIdProduct());
    }

    @Test
    void getIdSupplier() {
        assertEquals(1L,inventoryModel.getIdSupplier());
    }

    @Test
    void setIdSupplier() {
        InventoryEntity entity = new InventoryEntity();
        entity.setIdSupplier(2L);
        assertNotNull(entity.getIdSupplier());
        assertEquals(2L,entity.getIdSupplier());
    }
}