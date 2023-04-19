package com.acelerati.management_service.domain.util;

public class InventorySearchCriteriaUtil {

    private Long fromSalePrice;
    private Long toSalePrice;
    private Long categoryId;
    private Long brandId;

    public InventorySearchCriteriaUtil(Long fromSalePrice, Long toSalePrice, Long categoryId, Long brandId) {
        this.fromSalePrice = fromSalePrice;
        this.toSalePrice = toSalePrice;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public Long getFromSalePrice() {
        return fromSalePrice;
    }

    public void setFromSalePrice(Long fromSalePrice) {
        this.fromSalePrice = fromSalePrice;
    }

    public Long getToSalePrice() {
        return toSalePrice;
    }

    public void setToSalePrice(Long toSalePrice) {
        this.toSalePrice = toSalePrice;
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
