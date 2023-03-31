package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import com.acelerati.management_service.domain.api.CartServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartSpringServiceImpl implements CartSpringService {

    private final CartServicePort cartServicePort;

    @Override
    public void addProductToCart(AddProductToCartDTO addProductToCartDTO) {
        //Long idUser = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        Long idUser = 1L;
        cartServicePort.addProductToCart(idUser,
                addProductToCartDTO.getIdProduct(),
                addProductToCartDTO.getAmount());
    }
}
