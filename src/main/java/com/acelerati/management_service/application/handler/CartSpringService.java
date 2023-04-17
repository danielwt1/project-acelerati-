package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.response.CartDTO;

public interface CartSpringService {

    void deleteProductCart(Long idProduct);
    CartDTO getCartByIdUser(Integer page,Integer elementpPerPage);
}
