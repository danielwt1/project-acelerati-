package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;

import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.*;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;

import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;

import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.*;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;
import com.acelerati.management_service.infraestructure.exception.UnavailableMicroserviceException;
import org.springframework.stereotype.Service;

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

    public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper, InventorySearchMapper inventorySearchMapper, PaginationRequestMapper paginationRequestMapper, PaginationResponseMapper paginationResponseMapper, ProductFeignClientPort productFeignClientPort) {
        this.inventoryServicePort = inventoryServicePort;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.inventorySearchMapper = inventorySearchMapper;
        this.paginationRequestMapper = paginationRequestMapper;
        this.paginationResponseMapper = paginationResponseMapper;
        this.productFeignClientPort = productFeignClientPort;
    }
    @Override
     public void addInventory(List<InventoryDTO> inventoryDTO) {
          this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));
     }

     @Override
     public void updateProductSalePrice(InventoryUpdateRequestDTO inventoryDTO) {
          this.inventoryServicePort.updatePriceSale(this.inventoryRequestMapper.toModel(inventoryDTO));
     }

     @Override
     public FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteriaDTO,
                                                        PaginationDTO paginationDTO) throws UnavailableMicroserviceException {
          InventorySearchCriteriaUtil criteriaUtil = inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO);
          PaginationUtil paginationUtil = paginationRequestMapper.toPaginationUtil(paginationDTO);

          // Do the query against the database and update the paginator
          List<InventoryResponseDTO> inventoriesResponse =
                  inventorySearchMapper.toDTOList(
                          inventoryServicePort.getInventoriesBy(criteriaUtil, paginationUtil));

         // Fetch products from the corresponding microservice
         List<ProductDTO> productDTOS = fetchProductsFromMicroservice(0, 1000);

         // Filter by brand and category if they were specified
         if (criteriaUtil.getBrandId() != null) {
             productDTOS = productDTOS.stream()
                     .filter(productDTO -> productDTO.getIdBrand().equals(criteriaUtil.getBrandId()))
                     .collect(Collectors.toList());
         }

         if (criteriaUtil.getCategoryId() != null) {
             productDTOS = productDTOS.stream()
                     .filter(productDTO -> productDTO.getIdCategory().equals(criteriaUtil.getCategoryId()))
                     .collect(Collectors.toList());
         }

         PaginationResponseDTO paginationResponse = paginationResponseMapper.toResponseDTO(paginationUtil);
         return new FilterInventoryResponseDTO(prepareProductsFromStockDTO(inventoriesResponse, productDTOS),
                  paginationResponse);
     }

    @Override
    public List<ProductsForSaleDTO> getAllProductForSale(String name, String nombreMarca, String nombreCategoria,int page,int elementPerPage) throws UnavailableMicroserviceException {
        List<InventoryResponseDTO> inventoryList = this.inventorySearchMapper.toDTOList(this.inventoryServicePort.getAllInventoryWithStockAndSalePriceGreaterThan0());
        List<ProductDTO> products = this.productFeignClientPort.fetchProductsFromMicroservice(page, elementPerPage);
        List<ProductsForSaleDTO> dataFiltered = mergeData(inventoryList, products);
        return dataPaginated(dataFiltered,page,elementPerPage);
    }

    public List<ProductsForSaleDTO>dataPaginated(List<ProductsForSaleDTO> dataFiltered,int page,int elementPerPage){

        return dataFiltered.stream()
                .skip((long) (page - 1) * elementPerPage)
                .limit(elementPerPage)
                .collect(Collectors.toList());
    }

    public List<ProductsForSaleDTO> mergeData(List<InventoryResponseDTO> inventoryList, List<ProductDTO> products) {
        Map<Long, InventoryResponseDTO> dataInventory = inventoryList.stream()
                //Function.identity() is equal to element -> element
                .collect(Collectors.toMap(InventoryResponseDTO::getId, Function.identity()));
        return products.stream()
                .filter(product -> dataInventory.containsKey(product.getId()))
                .map(product -> new ProductsForSaleDTO(product.getId(),
                        product.getName(),
                        dataInventory.get(product.getId()).getSalePrice(),
                        dataInventory.get(product.getId()).getStock(),
                        product.getDescription())).collect(Collectors.toList());
    }

    private List<ProductsFromStockDTO> prepareProductsFromStockDTO(List<InventoryResponseDTO> inventories,
                                                                   List<ProductDTO> products) {
        Map<Long, InventoryResponseDTO> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(InventoryResponseDTO::getId, Function.identity()));
        return products.stream()
                .filter(product -> inventoryMap.containsKey(product.getId()))
                .map(product -> new ProductsFromStockDTO(inventoryMap.get(product.getId()), product.getIdBrand(),
                        product.getIdCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) throws UnavailableMicroserviceException {
        return productFeignClientPort.fetchProductsFromMicroservice(page, itemsNumber);
    }

}
