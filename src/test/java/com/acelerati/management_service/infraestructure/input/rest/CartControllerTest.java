package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.application.dto.response.CartDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    CartSpringService cartSpringService;
    @InjectMocks
    CartController cartController;

    public static final String USER_HEADER ="12345";
    public static final Integer PAGE = 2;
    public static final Integer ELEMENT_PER_PAGE = 10;


    @Test
    void whenTryingToRemoveProductFromCartThenStatusOk() {
        ResponseEntity<Void>response = this.cartController.deleteProductFromCart(USER_HEADER,2L);
        verify(this.cartSpringService).deleteProductCart(2L);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void WhenTryinggetCartThenThenResponseWithCart() {
        ResponseEntity<CartDTO> response = this.cartController.getCart(USER_HEADER,PAGE,ELEMENT_PER_PAGE);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenAddProductToCartDTO(){
        ResponseEntity<Void> response = this.cartController.addProductToCart(new AddProductToCartDTO(1L,1L));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}