package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.exception.InvalidFilterRangeException;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;

import com.acelerati.management_service.domain.util.PaginationUtil;


import com.acelerati.management_service.domain.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InventoryUseCase implements InventoryServicePort {
    public static final BigDecimal INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE = BigDecimal.valueOf(0);
    public static final Long NO_STOCK = 0L;
    public static final String INVENTORY_SEARCH_BY_PRODUCTS_WITHOUT_SALE_PRICE = "Products without sale price";
    public static final String INVENTORY_SEARCH_BY_PRODUCTS_WITHOUT_STOCK = "Products without stock";
    private final InventoryPersistencePort inventoryPersistencePort;

    public InventoryUseCase(InventoryPersistencePort inventoryPersistencePort) {
        this.inventoryPersistencePort = inventoryPersistencePort;
    }
    @Override
    public void addInventory(List<InventoryModel> inventoryModel) {
        inventoryModel.forEach(product -> {
            Optional<InventoryModel> foundProduct = this.inventoryPersistencePort.getElementById(product.getIdProduct());
            if (foundProduct.isEmpty()) {
                product.setSalePrice(INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE);
                this.inventoryPersistencePort.addInventory(product);
            } else {
                foundProduct.get().setStock(product.getStock() + foundProduct.get().getStock());
                this.inventoryPersistencePort.updateInventory(foundProduct.get());
            }
        });

    }


    @Override
    public List<InventoryModel> getInventoriesBy(InventorySearchCriteriaUtil inventorySearchCriteriaModel,
                                                 PaginationUtil paginationModel) throws InvalidFilterRangeException {
        if(inventorySearchCriteriaModel.getFromUnitPrice() > inventorySearchCriteriaModel.getToUnitPrice())
            throw new InvalidFilterRangeException("The From range must be greater than the To range");

        if (paginationModel.getPageSize() == null)
            paginationModel.setPageSize(PaginationUtil.DEFAULT_PAGE_SIZE);

        List<InventoryModel> inventories = inventoryPersistencePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel);
        paginationModel.updateAttributesFromListResults(inventories);

        return inventories;
    }

    @Override
    public List<InventoryModel> getAllInventoryWithStockAndSalePriceGreaterThan0() {
        return this.inventoryPersistencePort.getAllInventoryWithStockAndSalePriceGreaterThan0();
    }
    @Override
    public void updatePriceSale(InventoryModel inventoryModel) {
        InventoryModel foundProduct = this.inventoryPersistencePort.getElementById(inventoryModel.getIdProduct())
                .orElseThrow(()-> new ProductNotFoundException(String.format("The  Product named %s does not exist",inventoryModel.getName())));
        foundProduct.setSalePrice(inventoryModel.getSalePrice());
        this.inventoryPersistencePort.updateInventory(foundProduct);
    }

}
