package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.acelerati.management_service.application.utils.ApplicationDataSet.INVENTORY_1_RESPONSE_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsFromStockDTOTest {

    private static ProductsFromStockDTO productsFromStockDTO;

    @BeforeAll
    static void beforeAll() {
        productsFromStockDTO = new ProductsFromStockDTO(INVENTORY_1_RESPONSE_DTO.get(0), 1L, 1L);
    }

    @Test
    void whenInventoryResponseDTOAssignedItShouldBePreserved() {
        assertEquals(1L, productsFromStockDTO.getInventoryResponseDTO().getId());
        assertEquals("Mother board", productsFromStockDTO.getInventoryResponseDTO().getName());
        assertEquals(100L, productsFromStockDTO.getInventoryResponseDTO().getStock());
        assertEquals(BigDecimal.valueOf(300_000), productsFromStockDTO.getInventoryResponseDTO().getUnitPrice());
        assertEquals(BigDecimal.valueOf(350_000), productsFromStockDTO.getInventoryResponseDTO().getSalePrice());
        assertEquals(1L, productsFromStockDTO.getInventoryResponseDTO().getIdProduct());
        assertEquals(1L, productsFromStockDTO.getInventoryResponseDTO().getIdSupplier());
    }

    @Test
    void whenIdBrandAssignedItShouldBePreserved() {
        assertEquals(1L, productsFromStockDTO.getIdBrand());
    }

    @Test
    void whenIdCategoryAssignedItShouldBePreserved() {
        assertEquals(1L, productsFromStockDTO.getIdCategory());
    }
}
