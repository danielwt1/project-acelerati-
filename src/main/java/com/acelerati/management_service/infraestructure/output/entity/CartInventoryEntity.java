package com.acelerati.management_service.infraestructure.output.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart_Inventory")
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
    private Long amount;
    public CartInventoryEntity(Long id, CartEntity cart, InventoryEntity inventory, Long amount) {
        this.id = id;
        this.cart = cart;
        this.inventory = inventory;
        this.amount = amount;
    }

    public CartInventoryEntity() {
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
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
