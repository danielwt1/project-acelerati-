package com.acelerati.management_service.infraestructure.output.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartEntityTest {
    CartEntity cartEntity;

    @BeforeEach
    void setUp(){
        List<CartInventoryEntity> products = new ArrayList<>();
        this.cartEntity = new CartEntity(1L,1L, LocalDateTime.now(),products);
    }

    @Test
    void getIdCart() {
        assertEquals(1L,this.cartEntity.getIdCart());
    }

    @Test
    void setIdCart() {
        this.cartEntity.setIdCart(2L);
        assertEquals(2L,this.cartEntity.getIdCart());

    }

    @Test
    void getIdUser() {
        assertEquals(1L,this.cartEntity.getIdUser());

    }

    @Test
    void setIdUser() {
        this.cartEntity.setIdUser(2L);
        assertEquals(2L,this.cartEntity.getIdUser());

    }

    @Test
    void getLastUpdate() {
        assertNotNull(this.cartEntity.getLastUpdate());

    }

    @Test
    void setLastUpdate() {
        this.cartEntity.setLastUpdate(LocalDateTime.now());
        assertNotNull(this.cartEntity.getLastUpdate());

    }

    @Test
    void getProducts() {
        assertNotNull(this.cartEntity.getProducts());

    }

    @Test
    void setProducts() {
        List<CartInventoryEntity>productList = new ArrayList<>();
        this.cartEntity.setProducts(productList);
        assertNotNull(this.cartEntity.getProducts());

    }
}