package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class InventoryAndProductResponseDTO {
    private InventoryResponseDTO inventoryResponseDTO;
    private ProductFeignClientResponseDTO productFeignClientResponseDTO;
}
