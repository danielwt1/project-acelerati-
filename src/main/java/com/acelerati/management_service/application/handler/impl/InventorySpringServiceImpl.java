package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorySpringServiceImpl implements InventorySpringService {
     private final InventoryServicePort inventoryServicePort;
     private final InventoryRequestMapper inventoryRequestMapper;

     public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper) {
          this.inventoryServicePort = inventoryServicePort;
          this.inventoryRequestMapper = inventoryRequestMapper;
     }

     @Override
     public void addInventory(List<InventoryDTO> inventoryDTO) {
          this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));

     }
}
