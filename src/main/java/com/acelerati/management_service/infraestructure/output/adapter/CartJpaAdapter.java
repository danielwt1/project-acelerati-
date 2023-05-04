package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import com.acelerati.management_service.infraestructure.output.mapper.CartEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepository;
import com.acelerati.management_service.infraestructure.output.repository.CartRepository;

import java.util.Optional;

public class CartJpaAdapter implements CartPersistencePort {

    private final CartRepository cartRepository;
    private final CartEntityMapper cartEntityMapper;

    public CartJpaAdapter(CartRepository cartRepository, CartEntityMapper cartEntityMapper) {
        this.cartRepository = cartRepository;
        this.cartEntityMapper = cartEntityMapper;
    }

    @Override
    public Optional<CartModel> getCart(Long idUser) {
        return this.cartRepository.getCart(idUser).map(this.cartEntityMapper::toModel);
    }

    @Override
    public CartModel createCart(CartModel cart) {
        CartEntity cartEntity = cartRepository.save(cartEntityMapper.toEntity(cart));
        return cartEntityMapper.toModel(cartEntity);
    }

    @Override
    public void updateCart(CartModel cart) {
        cartRepository.save(cartEntityMapper.toEntity(cart));
    }

    @Override
    public Optional<CartModel> getCartByIdUser(Long idUser) {
        return Optional.empty();
    }
}
