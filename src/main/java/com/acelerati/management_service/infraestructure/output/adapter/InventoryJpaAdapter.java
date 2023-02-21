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
    public void addInventory(InventoryModel inventoryModel) {
        this.inventaryRepository.persistData(this.inventoryEntityMapper.toEntity(inventoryModel));
    }

    @Override
    public InventoryModel getElementById(Long idProduct) {
        return this.inventoryEntityMapper.toModel(this.inventaryRepository.getElementById(idProduct));
    }

    @Override
    public void updateInventory(InventoryModel inventoryModel) {
        this.inventaryRepository.updateInventory(this.inventoryEntityMapper.toEntity(inventoryModel));
    }
}
