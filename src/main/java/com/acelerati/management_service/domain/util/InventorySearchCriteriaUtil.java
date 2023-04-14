package com.acelerati.management_service.domain.util;

public class InventorySearchCriteriaUtil{

    private Long fromUnitPrice;
    private Long toUnitPrice;
    private String category;

    public InventorySearchCriteriaUtil(Long fromUnitPrice, Long toUnitPrice, String category) {
        this.fromUnitPrice = fromUnitPrice;
        this.toUnitPrice = toUnitPrice;
        this.category = category;
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
}