package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.InventoryModel;

public interface InventoryPersistencePort {
    InventoryModel addInventory(InventoryModel inventoryModel);
}
