package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartUseCaseTest {
    @Mock
    CartPersistencePort cartPersistencePort;
    @InjectMocks
    CartUseCase cartUseCase;
    CartModel cartModel;

    @BeforeEach()
    void setUp(){
        List<CartInventoryModel> products =new ArrayList<>();
        this.cartModel = new CartModel(1L,1L, LocalDateTime.now(),products);
    }

    @Test
    void whenFindCartByUserIdThenReturnCart() {
        CartInventoryModel cartInventoryModel = mock(CartInventoryModel.class);
        List<CartInventoryModel> products = Arrays.asList(cartInventoryModel,cartInventoryModel,cartInventoryModel);
        this.cartModel.setProducts(products);
        Optional<CartModel> cartModel = Optional.of(this.cartModel);
        when(this.cartPersistencePort.getCart(1L)).thenReturn(cartModel);
        CartModel response = this.cartUseCase.getCartByUserId(1L);
        assertEquals(3,response.getProducts().size());
    }

    @Test
    void whenFindCartByUserIdThenReturnCartEmpty() {
        Optional<CartModel> cartModel = Optional.of(this.cartModel);
        when(this.cartPersistencePort.getCart(1L)).thenReturn(cartModel);
        CartModel response = this.cartUseCase.getCartByUserId(1L);
        assertTrue(response.getProducts().isEmpty());
    }
}