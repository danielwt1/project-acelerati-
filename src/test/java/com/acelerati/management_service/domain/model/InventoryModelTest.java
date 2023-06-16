package com.acelerati.management_service.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryModelTest {
    InventoryModel inventoryModel = null;
    @BeforeEach
    void setUp() {
        inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
    }

    @Test
    void getId() {
        assertEquals(1L,inventoryModel.getIdInventory());
    }

    @Test
    void setId() {
        InventoryModel model = new InventoryModel();
        model.setIdInventory(2L);
        assertNotNull(model.getIdInventory());
        assertEquals(2L,model.getIdInventory());
    }

    @Test
    void getName() {
        assertEquals("producto",inventoryModel.getName());
    }

    @Test
    void setName() {
        InventoryModel model = new InventoryModel();
        model.setName("producto2");
        assertFalse(model.getName().isEmpty());
        assertEquals("producto2",model.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000,inventoryModel.getStock());
    }

    @Test
    void setStock() {
        InventoryModel model = new InventoryModel();
        model.setStock(20L);
        assertNotNull(model.getStock());
        assertEquals(20L,model.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000),inventoryModel.getUnitPrice());
    }

    @Test
    void setUnitPrice() {
        InventoryModel model = new InventoryModel();
        model.setUnitPrice(BigDecimal.valueOf(6000));
        assertNotNull(model.getUnitPrice());
        assertEquals(BigDecimal.valueOf(6000),model.getUnitPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals(BigDecimal.valueOf(6000),inventoryModel.getSalePrice());
    }

    @Test
    void setSalePrice() {
        InventoryModel model = new InventoryModel();
        model.setSalePrice(BigDecimal.valueOf(7000));
        assertNotNull(model.getSalePrice());
        assertEquals(BigDecimal.valueOf(7000),model.getSalePrice());
    }

    @Test
    void getIdProduct() {
        assertEquals(1L,inventoryModel.getIdProduct());
    }

    @Test
    void setIdProduct() {
        InventoryModel model = new InventoryModel();
        model.setIdProduct(2L);
        assertNotNull(model.getIdProduct());
        assertEquals(2L,model.getIdProduct());
    }

    @Test
    void getIdSupplier() {
        assertEquals(1L,inventoryModel.getIdSupplier());
    }

    @Test
    void setIdSupplier() {
        InventoryModel model = new InventoryModel();
        model.setIdSupplier(2L);
        assertNotNull(model.getIdSupplier());
        assertEquals(2L,model.getIdSupplier());
    }
}