package com.acelerati.management_service.domain.util;

public class InventorySearchCriteriaUtil {

    private Long fromUnitPrice;
    private Long toUnitPrice;
    private String category;
    private String brand;

    public InventorySearchCriteriaUtil(Long fromUnitPrice, Long toUnitPrice, String category, String brand) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
