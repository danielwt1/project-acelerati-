package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.InventoryModel;

import java.util.List;

public interface InventoryPersistencePort {
    void addInventory(List<InventoryModel> inventoryModel);
}
