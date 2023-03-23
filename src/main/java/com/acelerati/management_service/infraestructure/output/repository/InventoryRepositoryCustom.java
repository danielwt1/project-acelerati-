package com.acelerati.management_service.infraestructure.output.repository;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryRepositoryCustom <T>{
    void persistData(T entity);
     Optional<T> getElementById(Long id);
     void updateInventory(T entity);
    List<InventoryEntity> getInventoriesBy(InventorySearchCriteriaUtil inventorySearchCriteriaModel, PaginationUtil paginationModel);
    List<InventoryEntity>getAllInventoryWithStockAndSalePriceGreaterThan0();

}
