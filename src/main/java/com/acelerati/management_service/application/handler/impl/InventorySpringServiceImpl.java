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
                                                        PaginationDTO paginationDTO) {
          InventorySearchCriteriaUtil criteriaUtil = inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO);
          PaginationUtil paginationUtil = paginationRequestMapper.toPaginationUtil(paginationDTO);

          // Do the query against the database and update the paginator
          List<InventoryResponseDTO> inventoriesResponse =
                  inventorySearchMapper.toDTOList(
                          inventoryServicePort.getInventoriesBy(criteriaUtil, paginationUtil));

         // Fetch products from the corresponding microservice
         List<ProductDTO> productDTOS = fetchProductsFromMicroservice(0, 1000);

         productDTOS = applyBrandFilter(criteriaUtil.getBrand(), productDTOS);
         productDTOS = applyCategoryFilter(criteriaUtil.getCategory(), productDTOS);

         PaginationResponseDTO paginationResponse = paginationResponseMapper.toResponseDTO(paginationUtil);
          return new FilterInventoryResponseDTO(joinInventoryAndProduct(inventoriesResponse, productDTOS),
                  paginationResponse);
     }

    private List<ProductDTO> applyCategoryFilter(final String category, List<ProductDTO> productDTOS) {
        List<CategoryDTO> categoryDTOS;
        if (category != null) {
            categoryDTOS = fetchCategoriesFromMicroservice(0, 1000);
            final List<Long> categoryIDs;
            if (!categoryDTOS.isEmpty()) {
                categoryIDs = categoryDTOS.stream()
                        .filter(categoryDTO -> categoryDTO.getName().contains(category))
                        .mapToLong(CategoryDTO::getId)
                        .boxed()
                        .collect(Collectors.toList());
                productDTOS = productDTOS.stream()
                        .filter(productDTO -> categoryIDs.contains(productDTO.getIdCategory()))
                        .collect(Collectors.toList());
            }
        }
        return productDTOS;
    }

    private List<ProductDTO> applyBrandFilter(final String brand, List<ProductDTO> productDTOS) {
        List<BrandDTO> brandDTOS;
        if (brand != null) {
            brandDTOS = fetchBrandsFromMicroservice(0, 1000);
            final List<Long> brandIDs;
            if (!brandDTOS.isEmpty()) {
                brandIDs = brandDTOS.stream()
                        .filter(brandDTO -> brandDTO.getName().contains(brand))
                        .mapToLong(BrandDTO::getId)
                        .boxed()
                        .collect(Collectors.toList());
                productDTOS = productDTOS.stream()
                        .filter(productDTO -> brandIDs.contains(productDTO.getIdBrand()))
                        .collect(Collectors.toList());
            }
        }
        return productDTOS;
    }

    @Override
    public List<ProductsForSaleDTO> getAllProductForSale(String name, String nombreMarca, String nombreCategoria,int page,int elementPerPage) {
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
                .filter(product -> dataInventory.containsKey(Long.valueOf(product.getId())))
                .map(product -> new ProductsForSaleDTO(Long.valueOf(product.getId()),
                        product.getName(),
                        dataInventory.get(Long.valueOf(product.getId())).getSalePrice(),
                        dataInventory.get(Long.valueOf(product.getId())).getStock(),
                        product.getDescription())).collect(Collectors.toList());
    }

    private List<InventoryAndProductResponseDTO> joinInventoryAndProduct(List<InventoryResponseDTO> inventories,
                                                                         List<ProductDTO> products) {
        Map<Long, InventoryResponseDTO> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(InventoryResponseDTO::getId, Function.identity()));
        return products.stream()
                .filter(product -> inventoryMap.containsKey(product.getId()))
                .map(product -> new InventoryAndProductResponseDTO(inventoryMap.get(product.getId()), product))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) {
        return productFeignClientPort.fetchProductsFromMicroservice(page, itemsNumber);
    }

    @Override
    public List<CategoryDTO> fetchCategoriesFromMicroservice(Integer page, Integer itemsNumber) {
        return productFeignClientPort.fetchCategoriesFromMicroservice(page, itemsNumber);
    }

    @Override
    public List<BrandDTO> fetchBrandsFromMicroservice(Integer page, Integer itemsNumber) {
        return productFeignClientPort.fetchBrandsFromMicroservice(page, itemsNumber);
    }

}
