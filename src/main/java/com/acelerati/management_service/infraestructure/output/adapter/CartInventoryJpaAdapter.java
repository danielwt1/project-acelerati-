package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;
import com.acelerati.management_service.infraestructure.output.mapper.CartInventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.CycleAvoidingMappingContext;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepository;

import java.util.Optional;

public class CartInventoryJpaAdapter implements CartInventoryPersistencePort {
    private final CartInventoryRepository cartInventoryRepository;
    private final CartInventoryEntityMapper cartInventoryEntityMapper;

    public CartInventoryJpaAdapter(CartInventoryRepository cartInventoryRepository, CartInventoryEntityMapper cartInventoryEntityMapper) {
        this.cartInventoryRepository = cartInventoryRepository;
        this.cartInventoryEntityMapper = cartInventoryEntityMapper;
    }

    @Override
    public Optional<CartInventoryModel> findByIdProduct(Long idProduct) {
        return this.cartInventoryRepository.getCartInventoryByIdProduct(idProduct)
                .map(product ->this.cartInventoryEntityMapper.toModel(product,new CycleAvoidingMappingContext()));
    }

    @Override
    public void deleteProductFromCart(CartInventoryModel cartInventoryModel) {
        this.cartInventoryRepository.deleteProductFromCart(this.cartInventoryEntityMapper.toEntity(cartInventoryModel, new CycleAvoidingMappingContext()));
    }

    @Override
    public void saveCartItem(CartInventoryModel cartInventoryModel) {
        cartInventoryRepository.save(cartInventoryEntityMapper.toEntity(cartInventoryModel, new CycleAvoidingMappingContext()));
    }
}
