package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.request.InventoryDTO;

public interface InventorySpringService {
    void addInventory(InventoryDTO inventoryDTO);
}
