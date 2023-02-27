package com.acelerati.management_service.infraestructure.output.repository;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryRepositoryCustom <T>{
    void persistData(T entity);
     Optional<T> getElementById(Long id);
     void updateInventory(T entity);
    List<InventoryEntity> getInventoriesBy(InventorySearchCriteriaModel inventorySearchCriteriaModel, PaginationModel paginationModel);
}
