package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.spi.CartPersistencePort;

public class CartUseCase implements CartServicePort {
    private final CartPersistencePort cartPersistencePort;

    public CartUseCase(CartPersistencePort cartPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
    }

    @Override
    public CartModel getCartByUserId(Long idUser) {
        return this.cartPersistencePort.getCart(idUser).orElseGet(CartModel::new);
    }
}
