package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.CartInventoryServicePort;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;


public class CartInventoryUseCase implements CartInventoryServicePort {
    private final CartInventoryPersistencePort cartInventoryPersistencePort;

    public CartInventoryUseCase(CartInventoryPersistencePort cartInventoryPersistencePort) {
        this.cartInventoryPersistencePort = cartInventoryPersistencePort;
    }

    @Override
    public void deleteProductCart(Long idProduct) {
       CartInventoryModel productFromCart =  this.cartInventoryPersistencePort.findByIdProduct(idProduct).orElseThrow(()->new ProductNotFoundException("Product not found "));
        this.cartInventoryPersistencePort.deleteProductFromCart(productFromCart);
    }
}
