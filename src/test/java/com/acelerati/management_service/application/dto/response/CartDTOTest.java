package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDTOTest {
    CartDTO cartDTO;
    @BeforeEach
    void setUp(){
        List<CartInventoryDTO> products =new ArrayList<>();
        this.cartDTO = new CartDTO(LocalDateTime.now(),products);
    }

    @Test
    void getLastUpdate() {
        assertNotNull(this.cartDTO.getLastUpdate());
    }

    @Test
    void getProducts() {
        assertNotNull(this.cartDTO.getProducts());

    }

    @Test
    void setLastUpdate() {
        this.cartDTO.setLastUpdate(LocalDateTime.now());
        assertNotNull(this.cartDTO.getLastUpdate());
    }

    @Test
    void setProducts() {
        List<CartInventoryDTO> productsList =new ArrayList<>();
        this.cartDTO.setProducts(productsList);
        assertNotNull(this.cartDTO.getProducts());

    }
}