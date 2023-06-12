package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartUseCaseTest {
    @Mock
    CartPersistencePort cartPersistencePort;

    @Mock
    InventoryPersistencePort inventoryPersistencePort;

    @Mock
    CartInventoryPersistencePort cartInventoryPersistencePort;

    @InjectMocks
    CartUseCase cartUseCase;
    CartModel cartModel;
    InventoryModel inventoryModel;

    @BeforeEach()
    void setUp(){
        List<CartInventoryModel> products =new ArrayList<>();
        this.cartModel = new CartModel(1L,1L, LocalDateTime.now(),products);
        this.inventoryModel = new InventoryModel(1L,"producto",5000L,
                BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
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

    @Test
    void whenAddedProductToEmptyCart(){
        Optional<InventoryModel> inventoryModel = Optional.of(this.inventoryModel);
        Optional<CartModel> cartModel =  Optional.of(this.cartModel);
        when(this.inventoryPersistencePort.getElementById(1L))
                .thenReturn(inventoryModel);
        when(this.cartPersistencePort.getCart(3L))
                .thenReturn(Optional.empty());
        when(this.cartPersistencePort.createCart(Mockito.any()))
                .thenReturn(cartModel.get());
        cartUseCase.addProductToCart(3L, 1L, 4L);
        verify(inventoryPersistencePort).getElementById(1L);
        verify(cartPersistencePort).getCart(3L);
        verify(cartPersistencePort).createCart(Mockito.any());
        verify(cartInventoryPersistencePort).saveCartItem(Mockito.any());
    }

    @Test
    void whenAddedProductToNotEmptyCart(){
        Optional<InventoryModel> inventoryModel = Optional.of(this.inventoryModel);
        Optional<CartModel> cartModel =  Optional.of(this.cartModel);
        when(this.inventoryPersistencePort.getElementById(1L))
                .thenReturn(inventoryModel);
        when(this.cartPersistencePort.getCart(1L))
                .thenReturn(cartModel);
        cartUseCase.addProductToCart(1L, 1L, 8L);
        verify(cartPersistencePort).getCart(1L);
    }

    @Test
    void whenProductNotFound(){
        when(this.inventoryPersistencePort.getElementById(inventoryModel.getIdProduct())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ProductNotFoundException.class,
                ()->cartUseCase.addProductToCart(1L, inventoryModel.getIdProduct(), 4L));
        verify(this.inventoryPersistencePort).getElementById(inventoryModel.getIdProduct());
    }
}