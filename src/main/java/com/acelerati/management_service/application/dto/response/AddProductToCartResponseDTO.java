package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddProductToCartResponseDTO {

    private final Long idProductCart;

    private final Long amount;

}
