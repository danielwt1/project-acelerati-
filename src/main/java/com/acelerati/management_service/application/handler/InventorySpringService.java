package com.acelerati.management_service.application.handler;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;

import java.util.List;

public interface InventorySpringService {
    void addInventory(List<InventoryDTO> inventoryDTO);

    List<InventoryResponseDTO> getInventoriesBy(InventorySearchCriteriaDTO searchCriteria);
}
