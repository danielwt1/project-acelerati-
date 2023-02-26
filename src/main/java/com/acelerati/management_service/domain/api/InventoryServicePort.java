package com.acelerati.management_service.domain.api;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;

import java.util.List;

public interface InventoryServicePort {
    void addInventory(List<InventoryModel> inventoryModel);
    List<InventoryModel> getInventoriesBy(InventorySearchCriteriaModel inventorySearchCriteriaModel);
}
