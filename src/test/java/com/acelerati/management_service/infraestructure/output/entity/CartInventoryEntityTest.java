package com.acelerati.management_service.infraestructure.output.entity;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CartInventoryEntityTest {

    CartInventoryEntity cartInventoryEntity;
    CartEntity cartEntity;
    InventoryEntity inventory;
    @BeforeEach
    void setup(){
        this.cartEntity =new CartEntity();
        this.inventory = new InventoryEntity();
        this.cartInventoryEntity = new CartInventoryEntity(1L,this.cartEntity,this.inventory,2L);
    }
    @Test
    void constructor(){
        CartInventoryEntity cartInventoryEntity1 = new CartInventoryEntity();
        assertNotNull(cartInventoryEntity1);
    }
    @Test
    void getId() {
        assertEquals(1l,this.cartInventoryEntity.getId());
    }

    @Test
    void setId() {
        this.cartInventoryEntity.setId(2L);
        assertEquals(2l,this.cartInventoryEntity.getId());
    }

    @Test
    void getCart() {
        assertEquals(this.cartEntity,this.cartInventoryEntity.getCart());

    }

    @Test
    void setCart() {
        CartEntity cart =new CartEntity();
        this.cartInventoryEntity.setCart(cart);
        assertEquals(cart,this.cartInventoryEntity.getCart());
    }

    @Test
    void getInventory() {
        assertEquals(this.inventory,this.cartInventoryEntity.getInventory());
    }

    @Test
    void setInventory() {
        InventoryEntity inventoryEntity =new InventoryEntity();
        this.cartInventoryEntity.setInventory(inventoryEntity);
        assertEquals(inventoryEntity,this.cartInventoryEntity.getInventory());

    }

    @Test
    void getAmount() {
        assertEquals(2L,this.cartInventoryEntity.getAmount());

    }

    @Test
    void setAmount() {
        this.cartInventoryEntity.setAmount(1L);
        assertEquals(1L,this.cartInventoryEntity.getAmount());

    }
}