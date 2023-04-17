package com.acelerati.management_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Schema(name = "InventoryResponse",
        description = "Represents a product stored in the inventory")
public class InventoryResponseDTO {
    @Schema(description = "The unique traceable identifier of the product in the inventory", example = "5")
    private final Long id;
    @Schema(description = "The name of the product being stored in the inventory.", example = "Mother Board")
    private final String name;
    @Schema(description = "The amount of available product stored in the inventory.", example = "300")
    private final Long stock;
    @Schema(description = "The price of the product when being sold by the supplier.", example = "350000")
    private final BigDecimal unitPrice;
    @Schema(description = "The price of the product when going to be sold to clients.", example = "400000")
    private final BigDecimal salePrice;
    @Schema(description = "The unique traceable identifier of the product", example = "22")
    private final Long idProduct;
    @Schema(description = "The unique traceable identifier of the supplier who provided this product", example = "66")
    private final Long idSupplier;

}
