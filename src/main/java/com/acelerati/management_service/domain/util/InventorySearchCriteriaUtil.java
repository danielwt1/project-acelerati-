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

    public Long getToSalePrice() {
        return toSalePrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }
}
