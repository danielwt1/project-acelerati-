package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartInventoryUseCaseTest {
    @Mock
    CartInventoryPersistencePort cartInventoryPersistencePort;
    @InjectMocks
    CartInventoryUseCase cartInventoryUseCase;
    static final Long USER_ID=1L;
    @Test
    void whenFindCartThenReturnCartAndRemoveProductFromCart(){
        CartInventoryModel cartInventoryModel = mock(CartInventoryModel.class);
        when(this.cartInventoryPersistencePort.findByIdProduct(USER_ID)).thenReturn(Optional.of(cartInventoryModel));
        this.cartInventoryUseCase.deleteProductCart(USER_ID);
        verify(cartInventoryPersistencePort).deleteProductFromCart(cartInventoryModel);
    }
    @Test
    void whenFindCartThenThrowProductNotFoundException(){
        CartInventoryModel cartInventoryModel = mock(CartInventoryModel.class);
        when(this.cartInventoryPersistencePort.findByIdProduct(USER_ID)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                ()->this.cartInventoryUseCase.deleteProductCart(USER_ID));
    }


}