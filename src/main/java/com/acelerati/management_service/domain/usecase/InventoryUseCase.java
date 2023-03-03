package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.infraestructure.ExceptionHandler.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InventoryUseCase implements InventoryServicePort {

    private final InventoryPersistencePort inventoryPersistencePort;
    private static final BigDecimal INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE = BigDecimal.valueOf(0);

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
    public void updatePriceSale(InventoryModel inventoryModel) {
        InventoryModel foundProduct = this.inventoryPersistencePort.getElementById(inventoryModel.getIdProduct())
                .orElseThrow(()-> new ProductNotFoundException(String.format("The  Product named %s does not exist",inventoryModel.getName())));
        foundProduct.setSalePrice(inventoryModel.getSalePrice());
        this.inventoryPersistencePort.updateInventory(foundProduct);
    }


}
