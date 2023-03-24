package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.response.AddProductToCartResponseDTO;

public interface CartSpringService {

    void addProductToCart(AddProductToCartResponseDTO addProductToCartResponseDTO);

}
