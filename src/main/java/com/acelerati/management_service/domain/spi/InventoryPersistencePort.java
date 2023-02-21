package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.InventoryModel;

import java.util.List;

public interface InventoryPersistencePort {
    void addInventory(InventoryModel inventoryModel);
    InventoryModel getElementById(Long idProduct);
    void updateInventory(InventoryModel inventoryModel);

}
