package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.PaginationResponseDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.application.mapper.PaginationRequestMapper;
import com.acelerati.management_service.application.mapper.PaginationResponseMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventorySpringServiceImpl implements InventorySpringService {
     private final InventoryServicePort inventoryServicePort;
     private final InventoryRequestMapper inventoryRequestMapper;
     private final InventorySearchMapper inventorySearchMapper;
     private final PaginationRequestMapper paginationRequestMapper;
     private final PaginationResponseMapper paginationResponseMapper;

     public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper,
                                       InventorySearchMapper inventorySearchMapper, PaginationRequestMapper paginationRequestMapper,
                                       PaginationResponseMapper paginationResponseMapper) {
          this.inventoryServicePort = inventoryServicePort;
          this.inventoryRequestMapper = inventoryRequestMapper;
          this.inventorySearchMapper = inventorySearchMapper;
          this.paginationRequestMapper = paginationRequestMapper;
          this.paginationResponseMapper = paginationResponseMapper;
     }
     @Override
     public void addInventory(List<InventoryDTO> inventoryDTO) {
          this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));

     }

     @Override
     public FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteriaDTO, PaginationDTO paginationDTO) {
          InventorySearchCriteriaModel inventorySearchCriteriaModel = inventorySearchMapper.toModel(searchCriteriaDTO);
          PaginationModel paginationModel = paginationRequestMapper.toModel(paginationDTO);
          List<InventoryModel> inventoryModels = inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel);
          List<InventoryResponseDTO> inventoriesResponse = inventorySearchMapper.toDTOList(inventoryModels);
          PaginationResponseDTO paginationResponse = paginationResponseMapper.toResponseDTO(paginationModel);
          return new FilterInventoryResponseDTO(inventoriesResponse, paginationResponse);
     }
}
