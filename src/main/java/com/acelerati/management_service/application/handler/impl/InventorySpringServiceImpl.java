package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.*;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.*;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InventorySpringServiceImpl implements InventorySpringService {
     private final InventoryServicePort inventoryServicePort;
     private final InventoryRequestMapper inventoryRequestMapper;
     private final InventorySearchMapper inventorySearchMapper;
     private final PaginationRequestMapper paginationRequestMapper;
     private final PaginationResponseMapper paginationResponseMapper;
     private final ProductFeignClientPort productFeignClientPort;
     private final ProductResponseMapper productResponseMapper;

     public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper,
                                       InventorySearchMapper inventorySearchMapper, PaginationRequestMapper paginationRequestMapper,
                                       PaginationResponseMapper paginationResponseMapper, ProductFeignClientPort productFeignClientPort,
                                       ProductResponseMapper productResponseMapper) {
          this.inventoryServicePort = inventoryServicePort;
          this.inventoryRequestMapper = inventoryRequestMapper;
          this.inventorySearchMapper = inventorySearchMapper;
          this.paginationRequestMapper = paginationRequestMapper;
          this.paginationResponseMapper = paginationResponseMapper;
          this.productFeignClientPort = productFeignClientPort;
          this.productResponseMapper = productResponseMapper;
     }
     @Override
     public void addInventory(List<InventoryDTO> inventoryDTO) {
          this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));

     }

     @Override
     public FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteriaDTO,
                                                        PaginationDTO paginationDTO) {
          InventorySearchCriteriaModel inventorySearchCriteriaModel = inventorySearchMapper.toModel(searchCriteriaDTO);
          PaginationModel paginationModel = paginationRequestMapper.toModel(paginationDTO);

          // Do the query against the database and update the paginator
          List<InventoryResponseDTO> inventoriesResponse =
                  inventorySearchMapper.toDTOList(
                          inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel));

          // Fetch products from the corresponding microservice
          List<ProductFeignClientResponseDTO> feignClientResponseDTOS = fetchProductsFromMicroservice();

          PaginationResponseDTO paginationResponse = paginationResponseMapper.toResponseDTO(paginationModel);
          return new FilterInventoryResponseDTO(joinInventoryAndProduct(inventoriesResponse, feignClientResponseDTOS),
                  paginationResponse);
     }

     private List<InventoryAndProductResponseDTO> joinInventoryAndProduct(List<InventoryResponseDTO> inventories,
                                                                          List<ProductFeignClientResponseDTO> products) {
          Map<Long, InventoryResponseDTO> inventoryMap = inventories.stream()
                          .collect(Collectors.toMap(InventoryResponseDTO::getId, Function.identity()));
          return products.stream()
                  .filter(product -> inventoryMap.containsKey(product.getId()))
                  .map(product -> new InventoryAndProductResponseDTO(inventoryMap.get(product.getId()), product))
                  .collect(Collectors.toList());
     }

     @Override
     public List<ProductFeignClientResponseDTO> fetchProductsFromMicroservice() {
          return productResponseMapper.toProductFeignClientResponseDTOList(productFeignClientPort.fetchProductsFromMicroservice());
     }
}
