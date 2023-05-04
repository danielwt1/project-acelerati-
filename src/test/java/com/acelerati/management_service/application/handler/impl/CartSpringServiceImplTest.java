package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.dto.response.CartDTO;
import com.acelerati.management_service.application.dto.response.CartInventoryDTO;
import com.acelerati.management_service.application.mapper.CartResponseMapper;
import com.acelerati.management_service.application.util.UtilGlobalMethods;
import com.acelerati.management_service.domain.api.CartInventoryServicePort;
import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartSpringServiceImplTest {
    @Mock
    CartInventoryServicePort cartInventoryServicePort;
    @Mock
    CartServicePort cartServicePort;
    @Mock
    CartResponseMapper cartResponseMapper;
    @InjectMocks
    CartSpringServiceImpl cartSpringService;
    static final Long ID_USER =1L;

    @Test
    void whendeleteProductCartThenRemoveProductFromCart() {
        this.cartSpringService.deleteProductCart(ID_USER);
        verify(this.cartInventoryServicePort).deleteProductCart(ID_USER);
    }

    @Test
    void whenFindCartByIdUserThenReturnCartPerUser() {
        CartModel cart = mock(CartModel.class);
        CartDTO cartDTO = mock(CartDTO.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("1");
        SecurityContextHolder.setContext(securityContext);
        when(this.cartServicePort.getCartByUserId(ID_USER)).thenReturn(cart);
        when(this.cartResponseMapper.toDTO(cart)).thenReturn(cartDTO);
        List<CartInventoryDTO> products =new ArrayList<>();
        CartDTO response = this.cartSpringService.getCartByIdUser(1,10);
        assertNotNull(response.getProducts());

    }


}