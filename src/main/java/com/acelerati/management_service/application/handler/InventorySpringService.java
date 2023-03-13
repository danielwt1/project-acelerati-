package com.acelerati.management_service.application.handler;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;

import java.util.List;

public interface InventorySpringService {
    void addInventory(List<InventoryDTO> inventoryDTO);

    void updateProductSalePrice(InventoryUpdateRequestDTO inventoryDTO);
}
