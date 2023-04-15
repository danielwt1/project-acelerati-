package com.acelerati.management_service.infraestructure.output.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory", indexes = @Index(columnList = "id_product"))
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventory;
    private String name;
    private Long stock;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "id_supplier")
    private Long idSupplier;
    public InventoryEntity() {
    }

    public InventoryEntity(Long idInventory, String name, Long stock, BigDecimal unitPrice, BigDecimal salePrice, Long idProduct, Long idSupplier) {
        this.idInventory = idInventory;
        this.name = name;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.salePrice = salePrice;
        this.idProduct = idProduct;
        this.idSupplier = idSupplier;
    }

    public Long getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Long idInventory) {
        this.idInventory = idInventory;
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
