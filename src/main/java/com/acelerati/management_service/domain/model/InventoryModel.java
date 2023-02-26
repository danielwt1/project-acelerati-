package com.acelerati.management_service.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InventoryModel {
    private Long id;
    private String name;
    private Long stock;
    private BigDecimal unitPrice;
    private BigDecimal salePrice;
    private Long idProduct;
    private Long idSupplier;

    public InventoryModel() {
    }


    public InventoryModel(Long id, String name, Long stock, BigDecimal unitPrice, BigDecimal salePrice, Long idProduct, Long idSupplier) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.salePrice = salePrice;
        this.idProduct = idProduct;
        this.idSupplier = idSupplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }
}
