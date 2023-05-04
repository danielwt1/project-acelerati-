package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;

import java.util.Optional;

public interface CartInventoryRepositoryCustom <T>{

    Optional<T> getCartInventoryByIdProduct(Long idUser);

    void deleteProductFromCart(CartInventoryEntity productFromCart);
}
