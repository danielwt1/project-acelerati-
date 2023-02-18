package com.acelerati.management_service.domain.api;

import com.acelerati.management_service.domain.model.InventoryModel;

public interface InventoryServicePort {
    void addInventory(InventoryModel inventoryModel);
}
