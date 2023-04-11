package com.acelerati.management_service.domain.util;

public class InventorySearchCriteriaUtil {

    private Long fromUnitPrice;
    private Long toUnitPrice;
    private Long categoryId;
    private Long brandId;

    public InventorySearchCriteriaUtil(Long fromUnitPrice, Long toUnitPrice, Long categoryId, Long brandId) {
        this.fromUnitPrice = fromUnitPrice;
        this.toUnitPrice = toUnitPrice;
        this.categoryId = categoryId;
        this.brandId = brandId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
