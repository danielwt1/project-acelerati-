package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.impl.InventaryRepositoryImpl;

public class InventoryJpaAdapter implements InventoryPersistencePort {
    //Use repository
    private final InventaryRepositoryImpl inventaryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;

    public InventoryJpaAdapter(InventaryRepositoryImpl inventaryRepository, InventoryEntityMapper inventoryEntityMapper) {
        this.inventaryRepository = inventaryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
    }

    @Override
    public InventoryModel addInventory(InventoryModel inventoryModel) {
        return null;
    }
}
