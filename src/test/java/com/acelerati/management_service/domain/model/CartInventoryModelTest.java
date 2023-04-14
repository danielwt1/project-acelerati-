package com.acelerati.management_service.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CartInventoryModelTest {
    CartInventoryModel CartInventoryModel;
    CartModel cartModel;
    InventoryModel inventory;
    @BeforeEach
    void setUp(){
        this.cartModel =new CartModel();
        this.inventory = new InventoryModel();
        this.CartInventoryModel = new CartInventoryModel(1L,this.cartModel,this.inventory,2L);
    }
    @Test
    void getId() {
        assertEquals(1L,this.CartInventoryModel.getId());
    }

    @Test
    void setId() {
        this.CartInventoryModel.setId(2L);
        assertEquals(2L,this.CartInventoryModel.getId());
    }

    @Test
    void getCartModel() {
        assertNotNull(this.CartInventoryModel.getCartModel());
    }

    @Test
    void setCartModel() {
        CartModel cart = new CartModel();
        this.CartInventoryModel.setCartModel(cart);
        assertNotNull(this.CartInventoryModel.getCartModel());
    }

    @Test
    void getInventory() {
        assertNotNull(this.CartInventoryModel.getInventory());
    }

    @Test
    void setInventory() {
        InventoryModel inventoryModel = new InventoryModel();
        this.CartInventoryModel.setInventory(inventoryModel);
        assertNotNull(this.CartInventoryModel.getInventory());

    }

    @Test
    void getAmount() {
        assertEquals(2L,this.CartInventoryModel.getAmount());
    }

    @Test
    void setAmount() {
        this.CartInventoryModel.setAmount(1L);
        assertEquals(1L,this.CartInventoryModel.getAmount());
    }
}