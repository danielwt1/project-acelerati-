package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import com.acelerati.management_service.infraestructure.output.mapper.CartEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.CartRepository;

import java.util.Optional;

public class CartJpaAdapter implements CartPersistencePort {

    private final CartRepository cartRepo;
    private final CartEntityMapper cartEntityMapper;

    public CartJpaAdapter(CartRepository cartRepo, CartEntityMapper cartEntityMapper) {
        this.cartRepo = cartRepo;
        this.cartEntityMapper = cartEntityMapper;
    }

    @Override
    public Optional<CartModel> getCart(Long idUser) {
        return this.cartRepo.getCart(idUser).map(this.cartEntityMapper::toModel);
    }
}
