package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.CartInventoryModel;

import java.util.Optional;

public interface CartInventoryPersistencePort {

    Optional<CartInventoryModel> findByIdProduct(Long idProduct);
    void deleteProductFromCart(CartInventoryModel cartInventoryModel);
}
