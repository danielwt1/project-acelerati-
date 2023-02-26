package com.acelerati.management_service.infraestructure.output.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InventoryEntityTest {
    InventoryEntity inventoryEntity = null;
    @BeforeEach
    void setUp() {
        inventoryEntity = new InventoryEntity(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
    }

    @Test
    void getId() {
        assertEquals(1L,inventoryEntity.getId());
    }

    @Test
    void setId() {
        InventoryEntity entity = new InventoryEntity();
        entity.setId(2L);
        assertEquals(2L,entity.getId());
    }

    @Test
    void getName() {
        assertEquals("producto",inventoryEntity.getName());

    }

    @Test
    void setName() {
        InventoryEntity entity = new InventoryEntity();
        entity.setName("producto2");
        assertEquals("producto2",entity.getName());
    }

    @Test
    void getStock() {
        assertEquals(5000,inventoryEntity.getStock());
    }

    @Test
    void setStock() {
        InventoryEntity entity = new InventoryEntity();
        entity.setStock(20L);
        assertEquals(20L,entity.getStock());
    }

    @Test
    void getUnitPrice() {
        assertEquals(BigDecimal.valueOf(5000),inventoryEntity.getUnitPrice());
    }

    @Test
    void setUnitPrice() {
        InventoryEntity entity = new InventoryEntity();
        entity.setUnitPrice(BigDecimal.valueOf(4000));
        assertEquals(BigDecimal.valueOf(4000),entity.getUnitPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals(BigDecimal.valueOf(6000),inventoryEntity.getSalePrice());
    }

    @Test
    void setSalePrice() {
        InventoryEntity entity = new InventoryEntity();
        entity.setSalePrice(BigDecimal.valueOf(6000));
        assertEquals(BigDecimal.valueOf(6000),entity.getSalePrice());
    }

    @Test
    void getIdProduct() {
        assertEquals(1L,inventoryEntity.getIdProduct());
    }

    @Test
    void setIdProduct() {
        InventoryEntity entity = new InventoryEntity();
        entity.setIdProduct(2L);
        assertEquals(2L,entity.getIdProduct());
    }

    @Test
    void getIdSupplier() {
        assertEquals(1L,inventoryEntity.getIdSupplier());
    }

    @Test
    void setIdSupplier() {
        InventoryEntity entity = new InventoryEntity();
        entity.setIdSupplier(2L);
        assertEquals(2L,entity.getIdSupplier());
    }
}