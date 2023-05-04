package com.acelerati.management_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(name = "FilterInventoryResponse",
        description = "Wrapper object to represent the result of filtering the inventory by several criteria.")
public class FilterInventoryResponseDTO {
    @Schema(description = "The inventory objects found with the given criteria.", type = "array")
    private List<ProductsFromStockDTO> inventoryResponses;

    @Schema(implementation = PaginationResponseDTO.class, type = "object")
    private PaginationResponseDTO paginationResponse;
}
