package com.acelerati.management_service.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@ToString
@Schema(description = "Wrapper object to specify a Product to be added to the cart",
        name = "AddProductToCart")
public class AddProductToCartDTO {

    @NotNull
    @NotEmpty
    @Schema(description = "The Product ID", example = "2")
    @Min(value = 1, message = "The Product ID to add in the cart is invalid")
    private final Long idProduct;

    @NotNull
    @NotEmpty
    @Schema(description = "The amount of the product", example = "2")
    @Min(value = 1, message = "The amount of product to add in the cart is invalid")
    private final Long amount;
}


