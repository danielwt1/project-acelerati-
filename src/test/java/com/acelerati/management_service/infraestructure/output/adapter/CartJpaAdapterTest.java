package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import com.acelerati.management_service.infraestructure.output.mapper.CartEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartJpaAdapterTest {
    @Mock
    CartRepository cartRepo;
    @Mock
    CartEntityMapper cartEntityMapper;
    @InjectMocks
    CartJpaAdapter cartJpaAdapter;
    static final Long ID_USER = 1L;
    @Test
    void whenfindCartByIdUser() {
        CartModel cartModel = mock(CartModel.class);
        CartEntity cartEntity = mock(CartEntity.class);
        when(this.cartRepo.getCart(ID_USER)).thenReturn(Optional.of(cartEntity));
        when(this.cartEntityMapper.toModel(cartEntity)).thenReturn(cartModel);
        Optional<CartModel> response = this.cartJpaAdapter.getCart(ID_USER);
        assertTrue(response.isPresent());
    }
}