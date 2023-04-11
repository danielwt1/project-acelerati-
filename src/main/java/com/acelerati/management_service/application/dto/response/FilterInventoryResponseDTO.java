package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FilterInventoryResponseDTO {
    private List<ProductsFromStockDTO> inventoryResponseDTOs;
    private PaginationResponseDTO paginationResponseDTO;
}
