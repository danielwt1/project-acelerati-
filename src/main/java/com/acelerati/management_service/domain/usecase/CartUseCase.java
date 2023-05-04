package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public class CartUseCase implements CartServicePort {
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product selected not found";

    private final CartPersistencePort cartPersistencePort;
    private final InventoryPersistencePort inventoryPersistencePort;
    private final CartInventoryPersistencePort cartInventoryPersistencePort;

    public CartUseCase(CartPersistencePort cartPersistencePort, InventoryPersistencePort inventoryPersistencePort,
                       CartInventoryPersistencePort cartInventoryPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.inventoryPersistencePort = inventoryPersistencePort;
        this.cartInventoryPersistencePort = cartInventoryPersistencePort;
    }

    @Override
    public CartModel getCartByUserId(Long idUser) {
        return this.cartPersistencePort.getCart(idUser).orElseGet(CartModel::new);
    }

    @Override
    public void addProductToCart(Long idUser, Long idProduct, Long amount) {
        InventoryModel inventoryModel = inventoryPersistencePort.getElementById(idProduct)
                .orElseThrow(()->new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE));

        Optional<CartModel> cartModel = cartPersistencePort.getCartByIdUser(idUser);
        CartModel cart;

        if(cartModel.isEmpty()) {
            cartModel = Optional.of(new CartModel(null, idUser, LocalDateTime.now()));
            cart = cartPersistencePort.createCart(cartModel.get()); // At this point the Cart ID should not be null
        } else {
            cart = cartModel.get();
        }
        CartInventoryModel cartInventoryModel = new CartInventoryModel(null, cart, inventoryModel, amount);
        cartInventoryPersistencePort.saveCartItem(cartInventoryModel);
    }
}
