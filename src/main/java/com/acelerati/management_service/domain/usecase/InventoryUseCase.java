package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;

import java.util.List;

public class InventoryUseCase implements InventoryServicePort {

    private final InventoryPersistencePort inventoryPersistencePPort;

    public InventoryUseCase(InventoryPersistencePort inventoryPersistencePPort) {
        this.inventoryPersistencePPort = inventoryPersistencePPort;
    }

    @Override
    public void addInventory(List<InventoryModel> inventoryModel) {
        //Validation if dont exist product -> sale price == 0
        this.inventoryPersistencePPort.addInventory(inventoryModel);
    }
}
