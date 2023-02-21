package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;

import java.math.BigDecimal;
import java.util.List;

public class InventoryUseCase implements InventoryServicePort {

    private final InventoryPersistencePort inventoryPersistencePort;
    private static final BigDecimal INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE = BigDecimal.valueOf(0);
    public InventoryUseCase(InventoryPersistencePort inventoryPersistencePort) {
        this.inventoryPersistencePort = inventoryPersistencePort;
    }

    @Override
    public void addInventory(List<InventoryModel> inventoryModel) {
        //Validation if dont exist product -> sale price == 0
        inventoryModel.forEach(producto -> {
            InventoryModel  foundProduct= this.inventoryPersistencePort.getElementById(producto.getIdProduct());
            if ( foundProduct != null) {
                foundProduct.setStock(producto.getStock() + foundProduct.getStock());
                foundProduct.setSalePrice(producto.getSalePrice());
                this.inventoryPersistencePort.updateInventory(foundProduct);

            } else {
                producto.setSalePrice(INITIAL_VALUE_NEW_PRODUCT_SALE_PRICE);
                this.inventoryPersistencePort.addInventory(producto);
            }
        });

    }
}
