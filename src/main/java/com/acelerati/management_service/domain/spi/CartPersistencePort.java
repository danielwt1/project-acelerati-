package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;

import java.util.Optional;

public interface CartPersistencePort {

    void addProductToCart(CartInventoryModel cartInventoryModel);

    Optional<CartModel> getCartByIdUser(Long idUser);

}
