package com.acelerati.management_service.domain.model;

public class CartInventoryModel {

    private Long id;

    private CartModel cartModel;

    private InventoryModel inventory;

    private Long amount;

    public CartInventoryModel(Long id, CartModel cartModel, InventoryModel inventory, Long amount) {
        this.id = id;
        this.cartModel = cartModel;
        this.inventory = inventory;
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

    public InventoryModel getInventory() {
        return inventory;
    }

    public void setInventory(InventoryModel inventory) {
        this.inventory = inventory;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
