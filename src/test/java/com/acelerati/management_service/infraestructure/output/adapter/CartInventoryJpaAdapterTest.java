package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import com.acelerati.management_service.infraestructure.output.mapper.CartInventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.CycleAvoidingMappingContext;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartInventoryJpaAdapterTest {
    @Mock
     CartInventoryRepository cartInventoryRepository;
    @Mock
    CartInventoryEntityMapper cartInventoryEntityMapper;
    @InjectMocks
    CartInventoryJpaAdapter cartInventoryJpaAdapter;

    static final Long ID_PRODUCT = 1l;
    @Test
    void whenFindByIdProductThenReturnCartInventoryModel() {
        CartInventoryEntity cartInventoryEntity = mock(CartInventoryEntity.class);
        CartInventoryModel cartInventoryModel = mock(CartInventoryModel.class);
        when(this.cartInventoryRepository.getCartInventoryByIdProduct(ID_PRODUCT)).thenReturn(Optional.of(cartInventoryEntity));
        when(this.cartInventoryEntityMapper.toModel(eq(cartInventoryEntity), any(CycleAvoidingMappingContext.class))).thenReturn(cartInventoryModel);
        Optional<CartInventoryModel> response = this.cartInventoryJpaAdapter.findByIdProduct(ID_PRODUCT);
        assertTrue(response.isPresent());
    }

    @Test
    void whendeleteProductFromCartThenDeleteProductFromCart() {
        CartInventoryModel cartInventoryModel = mock(CartInventoryModel.class);
        this.cartInventoryJpaAdapter.deleteProductFromCart(cartInventoryModel);
    }
}