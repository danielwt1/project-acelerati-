package com.acelerati.management_service.application.dto.response;

import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartInventoryDTO {
    private Long id;
    private InventoryResponseDTO inventory;
}
