package com.acelerati.management_service.infraestructure.output.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_inventory")
public class SaleInventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSaleInventory;
    private Long amount;
    private BigDecimal exitPrice;

    @ManyToOne
    @JoinColumn(name = "idSale", nullable = false)
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "idInventory", nullable = false)
    private InventoryEntity inventory;

    public SaleInventoryEntity() {
    }

    public SaleInventoryEntity(Long idSaleInventory, Long amount, BigDecimal exitPrice, SaleEntity sale,
                               InventoryEntity inventory) {
        this.idSaleInventory = idSaleInventory;
        this.amount = amount;
        this.exitPrice = exitPrice;
        this.sale = sale;
        this.inventory = inventory;
    }

    public Long getIdSaleInventory() {
        return idSaleInventory;
    }

    public void setIdSaleInventory(Long idSaleInventory) {
        this.idSaleInventory = idSaleInventory;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(BigDecimal exitPrice) {
        this.exitPrice = exitPrice;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }
}
