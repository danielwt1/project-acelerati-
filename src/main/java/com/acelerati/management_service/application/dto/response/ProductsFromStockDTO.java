package com.acelerati.management_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "ProductsFromStock",
        description = "Wrapper object to represent a product in stock filtered by Brand and Category.")
public class ProductsFromStockDTO {
    @Schema(description = "The product found in the inventory", implementation = InventoryResponseDTO.class)
    private InventoryResponseDTO inventoryResponse;
    @Schema(description = "The brand of the product found in the inventory.", example = "5")
    private Long idBrand;
    @Schema(description = "The category of the product found in the inventory.", example = "10")
    private Long idCategory;
}
