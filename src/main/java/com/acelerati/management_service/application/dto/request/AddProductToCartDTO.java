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
@Schema(description = "Wrapper object to specify how the Inventory records will be filtered when searching",
        name = "InventorySearchCriteria")
public class AddProductToCartDTO {

    @NotNull
    @NotEmpty
    @Schema(description = "The Product ID. It can be omitted", example = "2")
    @Min(value = 1, message = "The category ID to search is invalid")
    private final Long idProduct;

    @NotNull
    @NotEmpty
    @Schema(description = "The amount of the product. It can be omitted", example = "2")
    @Min(value = 1, message = "The category ID to search is invalid")
    private final Long amount;
}


