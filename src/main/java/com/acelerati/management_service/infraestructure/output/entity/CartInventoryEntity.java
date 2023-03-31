package com.acelerati.management_service.infraestructure.output.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_inventory")
public class CartInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCart", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "idInventory", nullable = false)
    private InventoryEntity inventory;

    public CartInventoryEntity() {
    }

    public CartInventoryEntity(Long id, CartEntity cart, InventoryEntity inventory) {
        this.id = id;
        this.cart = cart;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }
}
