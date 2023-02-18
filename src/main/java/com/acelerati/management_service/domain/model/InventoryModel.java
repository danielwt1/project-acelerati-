package com.acelerati.management_service.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InventoryModel {
    private BigInteger id;
    private String name;
    private BigInteger stock;
    private BigDecimal unit_price;
    private BigDecimal sale_price;
    private BigInteger id_product;
    private BigInteger id_supplier;

    public InventoryModel() {
    }
    public InventoryModel(BigInteger id, String name, BigInteger stock, BigDecimal unit_price, BigDecimal sale_price, BigInteger id_product, BigInteger id_supplier) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.unit_price = unit_price;
        this.sale_price = sale_price;
        this.id_product = id_product;
        this.id_supplier = id_supplier;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getSale_price() {
        return sale_price;
    }

    public void setSale_price(BigDecimal sale_price) {
        this.sale_price = sale_price;
    }

    public BigInteger getId_product() {
        return id_product;
    }

    public void setId_product(BigInteger id_product) {
        this.id_product = id_product;
    }

    public BigInteger getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(BigInteger id_supplier) {
        this.id_supplier = id_supplier;
    }
}
