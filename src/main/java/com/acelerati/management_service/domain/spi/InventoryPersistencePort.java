package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;


import java.util.List;
import java.util.Optional;

public interface InventoryPersistencePort {
    void addInventory(InventoryModel inventoryModel);
    Optional<InventoryModel> getElementById(Long idProduct);
    void updateInventory(InventoryModel inventoryModel);
    List<InventoryModel> getInventoriesBy(InventorySearchCriteriaModel inventorySearchCriteriaModel, PaginationModel paginationModel);
}
