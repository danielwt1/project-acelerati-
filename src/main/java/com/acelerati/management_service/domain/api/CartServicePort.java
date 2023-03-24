package com.acelerati.management_service.domain.api;

import com.acelerati.management_service.domain.model.InventoryModel;

public interface CartServicePort {

    void addProductToCart(Long idUser, InventoryModel inventoryModel, Long amount);
}
