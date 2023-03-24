package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.dto.response.AddProductToCartResponseDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import com.acelerati.management_service.domain.usecase.CartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartSpringServiceImpl implements CartSpringService {

    private final CartUseCase cartUseCase;

    //private final Cart

    @Override
    public void addProductToCart(AddProductToCartResponseDTO addProductToCartResponseDTO) {

    }
}
