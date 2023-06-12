package com.acelerati.management_service.infraestructure.output.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sale")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;
    private Long idUser;
    private LocalDate saleDate;
    private String status;
    private Long idShipping;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SaleInventoryEntity> purchasedItems = new ArrayList<>();

    public SaleEntity() {
    }

    public SaleEntity(Long idSale, Long idUser, LocalDate saleDate, String status, Long idShipping,
                      List<SaleInventoryEntity> purchasedItems) {
        this.idSale = idSale;
        this.idUser = idUser;
        this.saleDate = saleDate;
        this.status = status;
        this.idShipping = idShipping;
        this.purchasedItems = purchasedItems;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdShipping() {
        return idShipping;
    }

    public void setIdShipping(Long idShipping) {
        this.idShipping = idShipping;
    }

    public List<SaleInventoryEntity> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<SaleInventoryEntity> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public void addPurchasedItem(SaleInventoryEntity saleInventoryEntity) {
        purchasedItems.add(saleInventoryEntity);
        saleInventoryEntity.setSale(this);
    }
}
