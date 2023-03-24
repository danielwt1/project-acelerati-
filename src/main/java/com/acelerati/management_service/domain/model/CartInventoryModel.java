package com.acelerati.management_service.domain.model;

public class CartInventoryModel {

    private Long id;

    private CartModel cartModel;

    private InventoryModel inventoryModel;

    private Long amount;

    public CartInventoryModel(Long id, CartModel cartModel, InventoryModel inventoryModel, Long amount) {
        this.id = id;
        this.cartModel = cartModel;
        this.inventoryModel = inventoryModel;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartModel getCartModel() {
        return cartModel;
    }

    public void setCartInventoryModel(CartModel cartModel) {
        this.cartModel = cartModel;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public void setInventoryModel(InventoryModel inventoryModel) {
        this.inventoryModel = inventoryModel;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
