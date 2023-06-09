package com.acelerati.management_service.domain.model;

import java.math.BigDecimal;

public class SaleInventoryModel {
    private Long idSaleInventory;
    private Long amount;
    private BigDecimal exitPrice;
    private SaleModel saleModel;
    private InventoryModel inventory;

    public SaleInventoryModel(Long idSaleInventory, Long amount, BigDecimal exitPrice, SaleModel saleModel,
                              InventoryModel inventory) {
        this.idSaleInventory = idSaleInventory;
        this.amount = amount;
        this.exitPrice = exitPrice;
        this.saleModel = saleModel;
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

    public SaleModel getSaleModel() {
        return saleModel;
    }

    public void setSaleModel(SaleModel saleModel) {
        this.saleModel = saleModel;
    }

    public InventoryModel getInventory() {
        return inventory;
    }

    public void setInventory(InventoryModel inventory) {
        this.inventory = inventory;
    }
}
