package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;

import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.*;
import com.acelerati.management_service.application.mapper.*;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.acelerati.management_service.application.utils.ApplicationDataSet.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventorySpringServiceImplTest {
    @Mock
    private InventoryServicePort inventoryServicePort;
    @Mock
    private InventoryRequestMapper inventoryRequestMapper;
    @Mock
    private InventorySearchMapper inventorySearchMapper;
    @Mock
    private PaginationRequestMapper paginationRequestMapper;
    @Mock
    private ProductResponseMapper productResponseMapper;
    @Mock
    private ProductFeignClientPort productFeignClientPort;
    @Mock
    private PaginationResponseMapper paginationResponseMapper;
    @Mock
    ProductFeignClientPort productFeignClient;
    @InjectMocks
    private InventorySpringServiceImpl inventoryImpl;

    @Test
    void whenCallSaveInventoryListThenSaveTest(){
        List<InventoryDTO> list = new ArrayList<>();
        this.inventoryImpl.addInventory(list);
    }

    @Test
    void whenCallMethodForMergeTwoListThenReturnNewListMergedTest() {
        List<InventoryResponseDTO> inventoryList =new ArrayList<>();
        InventoryResponseDTO inventory = mock(InventoryResponseDTO.class);
        inventoryList.add(inventory);
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO producto = mock(ProductDTO.class);
        products.add(producto);
        List<ProductsForSaleDTO> dataMerged = this.inventoryImpl.mergeData(inventoryList,products);
        assertEquals(ProductsForSaleDTO.class,dataMerged.get(0).getClass());
    }

    @Test
    void whenCallDataPaginatedThenReturnListPaginatedTest(){
        ProductsForSaleDTO products =mock(ProductsForSaleDTO.class);
        List<ProductsForSaleDTO> productsForSaleDTOList = Arrays.asList(products,products,products,products,products,products,products,products,products);
        List<ProductsForSaleDTO> dataPaginated = this.inventoryImpl.dataPaginated(productsForSaleDTOList,1,5);
        assertEquals(5,dataPaginated.size());
    }

    @Test
    void whenGetAllProductsForSaleThenResultListWithDataTest(){
        InventoryResponseDTO products = new InventoryResponseDTO(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryResponseDTO products2 = new InventoryResponseDTO(2L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryResponseDTO products3 = new InventoryResponseDTO(3L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);

        InventoryModel inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryModel inventoryModel2= new InventoryModel(2L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryModel inventoryModel3 = new InventoryModel(3L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);

        ProductDTO productDTO = new ProductDTO(1L,"Producto1","Producto1Des","Modelo1",2L,1L);
        ProductDTO productDTO2 = new ProductDTO(2L,"Producto1","Producto1Des","Modelo1",2L,1L);

        List<ProductDTO> productDTOS = Arrays.asList(productDTO,productDTO2);
        List<InventoryModel> inventoryModels = Arrays.asList(inventoryModel,inventoryModel2,inventoryModel3);
        List<InventoryResponseDTO> productsForSaleDTOList = Arrays.asList(products,products2,products3);
        when(this.inventoryServicePort.getAllInventoryWithStockAndSalePriceGreaterThan0()).thenReturn(inventoryModels);
        when(this.inventorySearchMapper.toDTOList(inventoryModels)).thenReturn(productsForSaleDTOList);
        when(this.productFeignClient.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(productDTOS);
        List<ProductsForSaleDTO> productsForSale = this.inventoryImpl.getAllProductForSale("producto","marca","nombreCat",1,5);
        assertEquals(2,productsForSale.size());
    }

    void whenGetInventoriesByCalledWithNoFiltersAndDefaultPagination_thenItReturnsAllProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        InventorySearchCriteriaModel inventorySearchCriteriaModel = new InventorySearchCriteriaModel(null, null, null, null);
        PaginationModel paginationModel = new PaginationModel(null, 1);
        when(inventorySearchMapper.toModel(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toModel(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(productResponseMapper.toProductFeignClientResponseDTOList(PRODUCT_MICROSERVICE_RESPONSE_1)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_DTO_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20, 1, 0, 3, 4L, "Showing 1 to 4 of 4 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);
        Assertions.assertNotNull(filterInventoryResponse, "Applying no filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying no filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponseDTO(), "Applying no filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(1L, filterInventoryResponse.getInventoryResponseDTOs().get(0).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponseDTOs().get(1).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponseDTOs().get(2).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponseDTOs().get(3).getInventoryResponseDTO().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithPriceRangeFilter_itShouldReturnProductsWithinTheSpecifiedRange() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(100_000L, 250_000L, null, null);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        InventorySearchCriteriaModel inventorySearchCriteriaModel = new InventorySearchCriteriaModel(100_000L, 250_000L, null, null);
        PaginationModel paginationModel = new PaginationModel(null, 1);

        when(inventorySearchMapper.toModel(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toModel(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(INVENTORY_2);
        when(inventorySearchMapper.toDTOList(INVENTORY_2)).thenReturn(INVENTORY_2_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(productResponseMapper.toProductFeignClientResponseDTOList(PRODUCT_MICROSERVICE_RESPONSE_1)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_DTO_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20, 1, 0, 2, 3L, "Showing 1 to 3 of 3 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying price filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying price filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponseDTO(), "Applying price filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponseDTOs().get(0).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponseDTOs().get(1).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponseDTOs().get(2).getInventoryResponseDTO().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithCategoryFilter_itShouldReturnCategoryMatchingOnlyProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, 1L, null);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        InventorySearchCriteriaModel inventorySearchCriteriaModel = new InventorySearchCriteriaModel(null, null, 1L, null);
        PaginationModel paginationModel = new PaginationModel(null, 1);

        when(inventorySearchMapper.toModel(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toModel(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        List<ProductModel> productsFilteredByCategory = PRODUCT_MICROSERVICE_RESPONSE_1.stream()
                .filter(productModel -> productModel.getIdCategory() == 1L)
                .collect(Collectors.toList());
        when(productFeignClientPort.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(productsFilteredByCategory);
        when(productResponseMapper.toProductFeignClientResponseDTOList(productsFilteredByCategory)).thenReturn(
                PRODUCT_MICROSERVICE_RESPONSE_DTO_1.stream()
                        .filter(productFeignClientResponseDTO -> productFeignClientResponseDTO.getIdCategory() == 1L)
                        .collect(Collectors.toList())
        );
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20, 1, 0, 2, 3L, "Showing 1 to 3 of 3 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying category filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying category filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponseDTO(), "Applying category filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(1L, filterInventoryResponse.getInventoryResponseDTOs().get(0).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponseDTOs().get(1).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponseDTOs().get(2).getInventoryResponseDTO().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithBrandFilter_itShouldReturnBrandMatchingOnlyProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, 2L);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        InventorySearchCriteriaModel inventorySearchCriteriaModel = new InventorySearchCriteriaModel(null, null, null, 2L);
        PaginationModel paginationModel = new PaginationModel(null, 1);

        when(inventorySearchMapper.toModel(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toModel(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        List<ProductModel> productsFilteredByBrand = PRODUCT_MICROSERVICE_RESPONSE_1.stream()
                .filter(productModel -> productModel.getIdBrand() == 2L)
                .collect(Collectors.toList());
        when(productFeignClientPort.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(productsFilteredByBrand);
        when(productResponseMapper.toProductFeignClientResponseDTOList(productsFilteredByBrand)).thenReturn(
                PRODUCT_MICROSERVICE_RESPONSE_DTO_1.stream()
                        .filter(productFeignClientResponseDTO -> productFeignClientResponseDTO.getIdBrand() == 2L)
                        .collect(Collectors.toList())
        );
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20, 1, 0, 1, 2L, "Showing 1 to 2 of 2 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying brand filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying brand filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponseDTO(), "Applying brand filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponseDTOs().get(0).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponseDTOs().get(1).getInventoryResponseDTO().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithPriceRangeAndCategory_itShouldReturnPriceAndCategoryMatchingProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(100_000L, 250_000L, 1L, null);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        InventorySearchCriteriaModel inventorySearchCriteriaModel = new InventorySearchCriteriaModel(100_000L, 250_000L, 1L, null);
        PaginationModel paginationModel = new PaginationModel(null, 1);

        when(inventorySearchMapper.toModel(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toModel(paginationDTO)).thenReturn(paginationModel);
        List<InventoryModel> inventoriesFilteredByPrice = INVENTORY_1.stream()
                        .filter(inventoryModel -> inventoryModel.getSalePrice().compareTo(BigDecimal.valueOf(100_000L)) >= 0 && inventoryModel.getSalePrice().compareTo(BigDecimal.valueOf(250_000L)) <= 0)
                        .collect(Collectors.toList());
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(inventoriesFilteredByPrice);
        when(inventorySearchMapper.toDTOList(inventoriesFilteredByPrice)).thenReturn(
                INVENTORY_1_RESPONSE_DTO.stream()
                        .filter(inventoryResponseDTO -> inventoryResponseDTO.getSalePrice().compareTo(BigDecimal.valueOf(100_000L)) >= 0 && inventoryResponseDTO.getSalePrice().compareTo(BigDecimal.valueOf(250_000L)) <= 0)
                        .collect(Collectors.toList())
        );
        List<ProductModel> productsFilteredByBrand = PRODUCT_MICROSERVICE_RESPONSE_1.stream()
                .filter(productModel -> productModel.getIdCategory() == 1L)
                .collect(Collectors.toList());
        when(productFeignClientPort.fetchProductsFromMicroservice(Mockito.anyInt(), Mockito.anyInt())).thenReturn(productsFilteredByBrand);
        when(productResponseMapper.toProductFeignClientResponseDTOList(productsFilteredByBrand)).thenReturn(
                PRODUCT_MICROSERVICE_RESPONSE_DTO_1.stream()
                        .filter(productFeignClientResponseDTO -> productFeignClientResponseDTO.getIdCategory() == 1L)
                        .collect(Collectors.toList())
        );
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20, 1, 0, 1, 2L, "Showing 1 to 2 of 2 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying price and category filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying price and category filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponseDTO(), "Applying price and category filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponseDTOs().get(0).getInventoryResponseDTO().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponseDTOs().get(1).getInventoryResponseDTO().getIdProduct());
    }

    @AfterEach
    void resetMocks() {
        reset(inventorySearchMapper);
        reset(paginationRequestMapper);
        reset(inventoryServicePort);
        reset(productFeignClientPort);
        reset(productResponseMapper);
        reset(paginationResponseMapper);
    }
}