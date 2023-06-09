package com.acelerati.management_service.infraestructure.output.repository;

import java.util.List;

public interface SaleInventoryRepositoryCustom<T> {
    void saveAllSaleInventories(List<T> saleInventoryEntities);
}
