package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ProductsFromStockDTO {
    private InventoryResponseDTO inventoryResponseDTO;
    private Long idBrand;
    private Long idCategory;
}
