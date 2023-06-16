package com.acelerati.management_service.domain.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private Long idCart;

    private Long idUser;

    private LocalDateTime lastUpdate;

    private List<CartInventoryModel> products = new ArrayList<>();

    public CartModel() {
    }


    public CartModel(Long idCart, Long idUser, LocalDateTime lastUpdate, List<CartInventoryModel> products) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.lastUpdate = lastUpdate;
        this.products = products;
    }

    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<CartInventoryModel> getProducts() {
        return products;
    }

    public void setProducts(List<CartInventoryModel> products) {
        this.products = products;
    }
}

