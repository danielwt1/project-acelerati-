package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepositoryCustom;

import java.util.List;

public class InventoryJpaAdapter implements InventoryPersistencePort {
    //Use repository
    private final InventoryRepositoryCustom<InventoryEntity>  inventaryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;

    public InventoryJpaAdapter(InventoryRepositoryCustom<InventoryEntity> inventaryRepository, InventoryEntityMapper inventoryEntityMapper) {
        this.inventaryRepository = inventaryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
    }

    @Override
    public void addInventory(List<InventoryModel> inventoryModel) {
        inventaryRepository.persistData(this.inventoryEntityMapper.toListEntity(inventoryModel));
    }
}
