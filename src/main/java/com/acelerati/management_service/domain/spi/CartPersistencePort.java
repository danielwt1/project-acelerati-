package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.CartModel;

import java.util.Optional;

public interface CartPersistencePort {
    Optional<CartModel> getCart(Long idUser);
}
