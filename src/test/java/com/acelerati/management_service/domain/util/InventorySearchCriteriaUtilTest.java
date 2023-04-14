package com.acelerati.management_service.domain.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventorySearchCriteriaUtilTest {
    private static InventorySearchCriteriaUtil inventorySearchCriteriaUtil;
    @BeforeAll
    static void beforeAll() {
        inventorySearchCriteriaUtil = new InventorySearchCriteriaUtil(100_000L, 250_000L, 1L, 2L);
    }

    @Test
    void whenUpdatingFieldsFromInventorySearchCriteriaUtilThenItsValuesMustChange() {
        inventorySearchCriteriaUtil.setFromSalePrice(150_000L);
        inventorySearchCriteriaUtil.setToSalePrice(600_000L);
        inventorySearchCriteriaUtil.setCategoryId(2L);
        inventorySearchCriteriaUtil.setBrandId(3L);

        assertEquals(150_000L, inventorySearchCriteriaUtil.getFromSalePrice());
        assertEquals(600_000L, inventorySearchCriteriaUtil.getToSalePrice());
        assertEquals(2L, inventorySearchCriteriaUtil.getCategoryId());
        assertEquals(3L, inventorySearchCriteriaUtil.getBrandId());
    }
}
