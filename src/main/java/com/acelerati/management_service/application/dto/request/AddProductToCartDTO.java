package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AddProductToCartDTO {

    @NotNull
    @NotEmpty
    @Min(1)
    private final Long idProduct;

    @NotNull
    @NotEmpty
    @Min(1)
    private final Long amount;
}
