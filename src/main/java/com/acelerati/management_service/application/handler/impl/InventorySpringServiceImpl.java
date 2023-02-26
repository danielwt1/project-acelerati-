package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventorySpringServiceImpl implements InventorySpringService {
     private final InventoryServicePort inventoryServicePort;
     private final InventoryRequestMapper inventoryRequestMapper;
     private final InventorySearchMapper inventorySearchMapper;
     public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper,
                                       InventorySearchMapper inventorySearchMapper) {
          this.inventoryServicePort = inventoryServicePort;
          this.inventoryRequestMapper = inventoryRequestMapper;
          this.inventorySearchMapper = inventorySearchMapper;
     }
     @Override
     public void addInventory(List<InventoryDTO> inventoryDTO) {
          this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));

     }

     @Override
     public List<InventoryResponseDTO> getInventoriesBy(InventorySearchCriteriaDTO searchCriteriaDTO) {
          InventorySearchCriteriaModel inventorySearchCriteriaModel = inventorySearchMapper.toModel(searchCriteriaDTO);
          List<InventoryModel> inventoryModels = inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel);
          return inventorySearchMapper.toDTOList(inventoryModels);
     }
}
