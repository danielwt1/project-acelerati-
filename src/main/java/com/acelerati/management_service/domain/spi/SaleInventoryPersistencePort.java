package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.SaleInventoryModel;

import java.util.List;

public interface SaleInventoryPersistencePort {

    void saveAllSaleInventories(List<SaleInventoryModel> saleInventoryModels);
}
