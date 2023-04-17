package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;

import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationRequestDTO;
import com.acelerati.management_service.application.dto.response.*;
import com.acelerati.management_service.application.mapper.*;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.exception.PageOutOfBoundsException;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.acelerati.management_service.domain.util.PaginationUtil.NO_RECORDS_FOUND;
import static com.acelerati.management_service.application.utils.ApplicationDataSet.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventorySpringServiceImplTest {
    @Mock
    private InventoryServicePort inventoryServicePort;
    @Mock
    private InventorySearchMapper inventorySearchMapper;
    @Mock
    InventoryRequestMapper inventoryRequestMapper;
    @Mock
    private PaginationRequestMapper paginationRequestMapper;
    @Mock
    private ProductFeignClientPort productFeignClientPort;
    @Mock
    private PaginationResponseMapper paginationResponseMapper;
    @InjectMocks
    private InventorySpringServiceImpl inventoryImpl;

    @Test
    void whenCallSaveInventoryListThenSaveTest(){
        List<InventoryDTO> list = new ArrayList<>();
        assertDoesNotThrow(() -> inventoryImpl.addInventory(list));
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
    void whenGetAllProductsForSaleThenResultListWithDataTest() {
        InventoryResponseDTO products = new InventoryResponseDTO(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryResponseDTO products2 = new InventoryResponseDTO(2L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 2L,1L);

        InventoryModel inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
        InventoryModel inventoryModel2= new InventoryModel(2L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 2L,1L);

        ProductDTO productDTO = new ProductDTO(1L,"Producto1","Producto1Des","Modelo1",2L,1L);
        ProductDTO productDTO2 = new ProductDTO(2L,"Producto1","Producto1Des","Modelo1",2L,1L);

        List<ProductDTO> productDTOS = Arrays.asList(productDTO,productDTO2);
        List<InventoryModel> inventoryModels = Arrays.asList(inventoryModel,inventoryModel2);
        List<InventoryResponseDTO> productsForSaleDTOList = Arrays.asList(products,products2);
        when(this.inventoryServicePort.getAllInventoryWithStockAndSalePriceGreaterThan0()).thenReturn(inventoryModels);
        when(this.inventorySearchMapper.toDTOList(inventoryModels)).thenReturn(productsForSaleDTOList);
        when(this.productFeignClientPort.fetchProductsFromMicroservice(1, 5)).thenReturn(productDTOS);
        List<ProductsForSaleDTO> productsForSale = this.inventoryImpl.getAllProductForSale("producto","marca","nombreCat",1,5);
        assertEquals(2,productsForSale.size());
    }

    @Test
    void whenGetInventoriesByCalledWithNoFiltersAndDefaultPagination_thenItReturnsAllProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(20L, 1L);
        InventorySearchCriteriaUtil inventorySearchCriteriaModel = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationModel = new PaginationUtil(20L, 1L);
        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(inventorySearchCriteriaModel);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 3L, 4L, "Showing 1 to 4 of 4 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);
        Assertions.assertNotNull(filterInventoryResponse, "Applying no filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponses(), "Applying no filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponse(), "Applying no filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(1L, filterInventoryResponse.getInventoryResponses().get(0).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponses().get(1).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponses().get(2).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponses().get(3).getInventoryResponse().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithPriceRangeFilter_itShouldReturnProductsWithinTheSpecifiedRange() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(100_000L, 250_000L, null, null);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(20L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(100_000L, 250_000L, null, null);
        PaginationUtil paginationModel = new PaginationUtil(20L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationModel)).thenReturn(INVENTORY_2);
        when(inventorySearchMapper.toDTOList(INVENTORY_2)).thenReturn(INVENTORY_2_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 2L, 3L, "Showing 1 to 3 of 3 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying price filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponses(), "Applying price filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponse(), "Applying price filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponses().get(0).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponses().get(1).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponses().get(2).getInventoryResponse().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithCategoryFilter_itShouldReturnCategoryMatchingOnlyProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, 1L, null);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(20L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, 1L, null);
        PaginationUtil paginationModel = new PaginationUtil(20L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 2L, 3L, "Showing 1 to 3 of 3 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying category filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponses(), "Applying category filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponse(), "Applying category filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(1L, filterInventoryResponse.getInventoryResponses().get(0).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponses().get(1).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponses().get(2).getInventoryResponse().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithBrandFilter_itShouldReturnBrandMatchingOnlyProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, 2L);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(20L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, 2L);
        PaginationUtil paginationModel = new PaginationUtil(20L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationModel)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying brand filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponses(), "Applying brand filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponse(), "Applying brand filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponses().get(0).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(4L, filterInventoryResponse.getInventoryResponses().get(1).getInventoryResponse().getIdProduct());
    }

    @Test
    void whenGetInventoriesByCalledWithPriceRangeAndCategory_itShouldReturnPriceAndCategoryMatchingProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(100_000L, 250_000L, 1L, null);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(20L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(100_000L, 250_000L, 1L, null);
        PaginationUtil paginationModel = new PaginationUtil(20L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationModel);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationModel)).thenReturn(INVENTORY_1_FILTERED_BY_CATEGORY_1_MODEL);
        when(inventorySearchMapper.toDTOList(INVENTORY_1_FILTERED_BY_CATEGORY_1_MODEL)).thenReturn(INVENTORY_1_FILTERED_BY_CATEGORY_1_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationModel)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying price and category filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponses(), "Applying price and category filter to the inventory should at least return an empty list ");
        Assertions.assertNotNull(filterInventoryResponse.getPaginationResponse(), "Applying price and category filter to the inventory should at least return an empty pagination object");

        Assertions.assertEquals(2L, filterInventoryResponse.getInventoryResponses().get(0).getInventoryResponse().getIdProduct());
        Assertions.assertEquals(3L, filterInventoryResponse.getInventoryResponses().get(1).getInventoryResponse().getIdProduct());
    }

    @Test
    void getInventoriesBy_onceTheSearchFinishedItShouldLeaveThePaginationReadyToServe() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        PaginationRequestDTO paginationDTO = new PaginationRequestDTO(4L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(4L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationDTO)).thenReturn(paginationUtil);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationUtil)).thenReturn(INVENTORY_1);
        when(inventorySearchMapper.toDTOList(INVENTORY_1)).thenReturn(INVENTORY_1_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationUtil)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        assertEquals("Showing 1 to 4 of 4 results.", paginationUtil.getDescription());
        assertEquals(4L, paginationUtil.getTotalResults());
        assertEquals(0, paginationUtil.getFirstResultIndex());
        assertEquals(3, paginationUtil.getLastResultIndex());
    }

    @Test
    void getInventoriesBy_whenNoResultsFoundThePaginationResponseShouldBeOnEmptyState() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        PaginationRequestDTO paginationRequestDTO = new PaginationRequestDTO(4L, 1L);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(4L, 1L);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationRequestDTO)).thenReturn(paginationUtil);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationUtil)).thenReturn(Collections.emptyList());
        when(inventorySearchMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationUtil)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationRequestDTO);
        assertEquals(NO_RECORDS_FOUND, paginationUtil.getDescription());
        assertEquals(0L, paginationUtil.getTotalResults());
        assertEquals(0L, paginationUtil.getFirstResultIndex());
        assertEquals(0L, paginationUtil.getLastResultIndex());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void getInventoriesBy_paginationInfoIsCorrectWhenMoreThanOnePageIsPresent(long pageNumber) {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);

        final long pageSize = 2;
        PaginationRequestDTO paginationRequestDTO = new PaginationRequestDTO(pageSize, pageNumber);
        PaginationUtil paginationUtil = new PaginationUtil(pageSize, pageNumber);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationRequestDTO)).thenReturn(paginationUtil);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationUtil)).thenReturn(INVENTORY_3);
        when(inventorySearchMapper.toDTOList(INVENTORY_3)).thenReturn(INVENTORY_3_RESPONSE_DTO);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationUtil)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationRequestDTO);
        String expectedDescription = "";
        int expectedFirstIndex = 0;
        int expectedLastIndex = 0;
        if (pageNumber == 1) {
            expectedDescription = "Showing 1 to 2 of 8 results.";
            expectedLastIndex = 1;
        } else if (pageNumber == 2) {
            expectedDescription = "Showing 3 to 4 of 8 results.";
            expectedFirstIndex = 2;
            expectedLastIndex = 3;
        } else if (pageNumber == 4) {
            expectedDescription = "Showing 7 to 8 of 8 results.";
            expectedFirstIndex = 6;
            expectedLastIndex = 7;
        }
        assertEquals(expectedDescription, paginationUtil.getDescription());
        assertEquals(8L, paginationUtil.getTotalResults());
        assertEquals(expectedFirstIndex, paginationUtil.getFirstResultIndex());
        assertEquals(expectedLastIndex, paginationUtil.getLastResultIndex());

        tearDown();
    }

    @ParameterizedTest
    @MethodSource("provideTwoLastPages_irregular")
    void getInventoriesBy_paginationInfoIsCorrectWhenLastPageHasLessResultsThanPageSize(
            long pageNumber, List<InventoryModel> returnedInventories, List<InventoryResponseDTO> returnedDTOS) {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);

        final long pageSize = 3;
        PaginationRequestDTO paginationRequestDTO = new PaginationRequestDTO(pageSize, pageNumber);
        PaginationUtil paginationUtil = new PaginationUtil(pageSize, pageNumber);


        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationRequestDTO)).thenReturn(paginationUtil);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationUtil)).thenReturn(returnedInventories);
        when(inventorySearchMapper.toDTOList(returnedInventories)).thenReturn(returnedDTOS);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(paginationResponseMapper.toResponseDTO(paginationUtil)).thenReturn(new PaginationResponseDTO(20L, 1L, 0L, 1L, 2L, "Showing 1 to 2 of 2 results."));

        inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationRequestDTO);
        String expectedDescription = "";
        int expectedFirstIndex = 0;
        int expectedLastIndex = 0;
        long expectedTotalResults = 0L;
        if (pageNumber == 2) {
            expectedDescription = "Showing 4 to 4 of 4 results.";
            expectedFirstIndex = 3;
            expectedLastIndex = 3;
            expectedTotalResults = 4L;
        } else if (pageNumber == 3) {
            expectedDescription = "Showing 7 to 8 of 8 results.";
            expectedFirstIndex = 6;
            expectedLastIndex = 7;
            expectedTotalResults = 8L;
        }
        assertEquals(expectedDescription, paginationUtil.getDescription());
        assertEquals(expectedTotalResults, paginationUtil.getTotalResults());
        assertEquals(expectedFirstIndex, paginationUtil.getFirstResultIndex());
        assertEquals(expectedLastIndex, paginationUtil.getLastResultIndex());

        tearDown();
    }

    private static Stream<Arguments> provideTwoLastPages_irregular() {
        return Stream.of(
                Arguments.of(2, INVENTORY_1, INVENTORY_1_RESPONSE_DTO),
                Arguments.of(3, INVENTORY_3, INVENTORY_3_RESPONSE_DTO)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOutOfBoundsPages")
    void getInventoriesBy_pageOutOfBoundsExceptionIsThrownWhenUserRequestTooHighPages(
            long pageNumber, List<InventoryModel> returnedInventories, List<InventoryResponseDTO> returnedDTOS) {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null, null);
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);

        final long pageSize = 3;
        PaginationRequestDTO paginationRequestDTO = new PaginationRequestDTO(pageSize, pageNumber);
        PaginationUtil paginationUtil = new PaginationUtil(pageSize, pageNumber);

        when(inventorySearchMapper.toCriteriaUtil(searchCriteriaDTO)).thenReturn(searchCriteria);
        when(paginationRequestMapper.toPaginationUtil(paginationRequestDTO)).thenReturn(paginationUtil);
        when(inventoryServicePort.getInventoriesBy(searchCriteria, paginationUtil)).thenReturn(returnedInventories);
        when(inventorySearchMapper.toDTOList(returnedInventories)).thenReturn(returnedDTOS);
        when(productFeignClientPort.fetchProductsFromMicroservice(0, 1000)).thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);

        PageOutOfBoundsException exception = assertThrows(PageOutOfBoundsException.class,
                () -> inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationRequestDTO));
        assertEquals("This record set cannot be navigated any further", exception.getMessage());

        tearDown();
    }

    private static Stream<Arguments> provideOutOfBoundsPages() {
        return Stream.of(
                Arguments.of(3, INVENTORY_1, INVENTORY_1_RESPONSE_DTO),
                Arguments.of(4, INVENTORY_3, INVENTORY_3_RESPONSE_DTO)
        );
    }

    private void tearDown() {
        Mockito.reset(inventoryServicePort);
    }

}