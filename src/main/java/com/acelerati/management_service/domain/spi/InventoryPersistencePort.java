package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.InventoryModel;


import java.util.Optional;

public interface InventoryPersistencePort {
    void addInventory(InventoryModel inventoryModel);
    Optional<InventoryModel> getElementById(Long idProduct);
    void updateInventory(InventoryModel inventoryModel);

}
