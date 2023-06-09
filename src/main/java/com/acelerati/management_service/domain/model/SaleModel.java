package com.acelerati.management_service.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleModel {
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_APPROVED = "APPROVED";
    public static final String STATUS_REJECTED = "REJECTED";

    private Long idSale;
    private Long idUser;
    private LocalDate saleDate;
    private String status;
    private Long idShipping;

    private List<SaleInventoryModel> purchasedItems;

    public SaleModel(Long idSale, Long idUser, LocalDate saleDate, String status, Long idShipping,
                     List<SaleInventoryModel> purchasedItems) {
        this.idSale = idSale;
        this.idUser = idUser;
        this.saleDate = saleDate == null ? LocalDate.now() : saleDate;
        this.status = status == null ? STATUS_PENDING : status;
        this.idShipping = idShipping;
        this.purchasedItems = purchasedItems == null ? new ArrayList<>() : purchasedItems;
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

    public List<SaleInventoryModel> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<SaleInventoryModel> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
}
