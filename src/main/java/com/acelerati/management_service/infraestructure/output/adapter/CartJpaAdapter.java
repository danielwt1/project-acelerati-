package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.spi.CartPersistencePort;

import java.util.Optional;

public class CartJpaAdapter implements CartPersistencePort {


    @Override
    public void addProductToCart(CartInventoryModel cartInventoryModel) {

    }

    @Override
    public Optional<CartModel> getCartByIdUser(Long idUser) {
        return Optional.empty();
    }
}
