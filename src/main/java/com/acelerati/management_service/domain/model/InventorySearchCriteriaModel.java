package com.acelerati.management_service.domain.model;

public class InventorySearchCriteriaModel {

    private Long fromUnitPrice;
    private Long toUnitPrice;
    private Long category;
    private Long brand;

    public InventorySearchCriteriaModel(Long fromUnitPrice, Long toUnitPrice, Long category, Long brand) {
        this.fromUnitPrice = fromUnitPrice;
        this.toUnitPrice = toUnitPrice;
        this.category = category;
        this.brand = brand;
    }

    public Long getFromUnitPrice() {
        return fromUnitPrice;
    }

    public void setFromUnitPrice(Long fromUnitPrice) {
        this.fromUnitPrice = fromUnitPrice;
    }

    public Long getToUnitPrice() {
        return toUnitPrice;
    }

    public void setToUnitPrice(Long toUnitPrice) {
        this.toUnitPrice = toUnitPrice;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }
}
