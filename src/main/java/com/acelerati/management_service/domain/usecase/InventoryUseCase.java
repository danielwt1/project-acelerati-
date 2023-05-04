package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.exception.InvalidFilterRangeException;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;

import com.acelerati.management_service.domain.util.PaginationUtil;

import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InventoryUseCase implements InventoryServicePort {
    public static final Logger logger = LoggerFactory.getLogger(InventoryUseCase.class);
    public static final BigDecimal INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE = BigDecimal.valueOf(0);
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
    public List<InventoryModel> getInventoriesBy(InventorySearchCriteriaUtil searchCriteria,
                                                 PaginationUtil paginationUtil) throws InvalidFilterRangeException {
        if (searchCriteria.getFromSalePrice() == null && searchCriteria.getToSalePrice() != null
        || searchCriteria.getFromSalePrice() != null && searchCriteria.getToSalePrice() == null) {
            throw new InvalidFilterRangeException("If one of either the From or To ranges is defined, the other must be defined too.");
        }

        if ((searchCriteria.getFromSalePrice() != null && searchCriteria.getToSalePrice() != null) &&
                (searchCriteria.getFromSalePrice() > searchCriteria.getToSalePrice())) {
            throw new InvalidFilterRangeException("The From range must be greater than the To range");
        }

        if (paginationUtil.getPageSize() == null) {
            logger.debug("Page size defaulted to {}", PaginationUtil.DEFAULT_PAGE_SIZE);
            paginationUtil.setPageSize(PaginationUtil.DEFAULT_PAGE_SIZE);
        }

        return inventoryPersistencePort.getInventoriesBy(searchCriteria, paginationUtil);
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
