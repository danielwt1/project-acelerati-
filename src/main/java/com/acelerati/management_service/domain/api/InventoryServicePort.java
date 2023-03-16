package com.acelerati.management_service.domain.api;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;

import java.util.List;

public interface InventoryServicePort {
    void addInventory(List<InventoryModel> inventoryModel);
    List<InventoryModel> getInventoriesBy(InventorySearchCriteriaUtil inventorySearchCriteriaModel, PaginationUtil paginationModel);
    List<InventoryModel>getAllInventoryWithStockAndSalePriceGreaterThan0();
}
