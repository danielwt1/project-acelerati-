package com.acelerati.management_service.infraestructure.output.adapter;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.util.PaginationUtil;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;


public class InventoryJpaAdapter implements InventoryPersistencePort {
    //Use repository
    private final InventoryRepository inventoryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;

    public InventoryJpaAdapter(InventoryRepository inventoryRepository, InventoryEntityMapper inventoryEntityMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
    }

    @Override
    public void addInventory(InventoryModel inventoryModel) {
        this.inventoryRepository.persistData(this.inventoryEntityMapper.toEntity(inventoryModel));
    }

    @Override
    public Optional<InventoryModel> getElementById(Long idProduct) {
        Optional<InventoryEntity> inventoryEntity = this.inventoryRepository.getElementById(idProduct);
        if(inventoryEntity.isPresent()){
            return Optional.of(this.inventoryEntityMapper.toModel(inventoryEntity.get()));
        }
        return Optional.empty();

    }

    @Override
    public void updateInventory(InventoryModel inventoryModel) {
        this.inventoryRepository.updateInventory(this.inventoryEntityMapper.toEntity(inventoryModel));
    }

    @Override
    public List<InventoryModel> getInventoriesBy(InventorySearchCriteriaUtil inventorySearchCriteriaModel, PaginationUtil paginationModel) {
        List<InventoryEntity> inventoryEntities = inventoryRepository.getInventoriesBy(inventorySearchCriteriaModel, paginationModel);
        return inventoryEntityMapper.toListModel(inventoryEntities);
    }

    @Override
    public List<InventoryModel> getAllInventoryWithStockAndSalePriceGreaterThan0() {
        return this.inventoryEntityMapper.toListModel(this.inventoryRepository.getAllInventoryWithStockAndSalePriceGreaterThan0());
    }
}
