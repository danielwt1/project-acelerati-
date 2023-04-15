package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.dto.response.CartDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import com.acelerati.management_service.application.mapper.CartResponseMapper;
import com.acelerati.management_service.application.util.UtilGlobalMethods;
import com.acelerati.management_service.domain.api.CartInventoryServicePort;
import com.acelerati.management_service.domain.api.CartServicePort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartSpringServiceImpl implements CartSpringService {
    private final CartInventoryServicePort cartInventoryServicePort;
    private final CartServicePort cartServicePort;
    private final CartResponseMapper cartResponseMapper;

    public CartSpringServiceImpl(CartInventoryServicePort cartInventoryServicePort, CartServicePort cartServicePort, CartResponseMapper cartResponseMapper) {
        this.cartInventoryServicePort = cartInventoryServicePort;
        this.cartServicePort = cartServicePort;
        this.cartResponseMapper = cartResponseMapper;
    }

    @Override
    public void deleteProductCart(Long idProduct) {
        this.cartInventoryServicePort.deleteProductCart(idProduct);
    }

    @Override
    public CartDTO getCartByIdUser(Integer page, Integer elementpPerPage) {
        Long idUser = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        CartDTO response =this.cartResponseMapper.toDTO(this.cartServicePort.getCartByUserId(idUser));
        response.setProducts(UtilGlobalMethods.dataPaginated(response.getProducts(),page,elementpPerPage));
        return response;
    }
}
