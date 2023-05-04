package com.acelerati.management_service.infraestructure.output.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cart", indexes = @Index(columnList = "idUser"))
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;
    private Long idUser;
    private LocalDateTime lastUpdate;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartInventoryEntity> products = new ArrayList<>();
    
    public CartEntity(Long idCart, Long idUser, LocalDateTime lastUpdate, List<CartInventoryEntity> products) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.lastUpdate = lastUpdate;
        this.products = products;
    }

    public CartEntity(Long idCart, Long idUser, LocalDateTime lastUpdate) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.lastUpdate = lastUpdate;
    }

    public CartEntity() {
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
    public List<CartInventoryEntity> getProducts() {
        return products;
    }
    public void setProducts(List<CartInventoryEntity> products) {
        this.products = products;
    }
}

