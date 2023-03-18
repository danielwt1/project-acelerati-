package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductsForSaleDTOTest {
    ProductsForSaleDTO productsForSaleDTO = null;
    @BeforeEach
    void setUp(){
        productsForSaleDTO = new ProductsForSaleDTO(1L,"producto1", BigDecimal.valueOf(3L),2L,"des");
    }
    @Test
    void getId() {
        assertEquals(1L,this.productsForSaleDTO.getId());
    }

    @Test
    void getName() {
        assertEquals("producto1",this.productsForSaleDTO.getName());
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.valueOf(3L),this.productsForSaleDTO.getPrice());
    }

    @Test
    void getStock() {
        assertEquals(2L,this.productsForSaleDTO.getStock());
    }

    @Test
    void getDescription() {
        assertEquals("des",this.productsForSaleDTO.getDescription());
    }
}