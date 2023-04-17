package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartEntity;

import java.util.Optional;


public interface CartRepositoryCustom {
    Optional<CartEntity> getCart(Long idUser);
}
