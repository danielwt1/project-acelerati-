package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.exception.InvalidFilterRangeException;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.acelerati.management_service.application.utils.ApplicationDataSet.INVENTORY_1;
import static com.acelerati.management_service.application.utils.ApplicationDataSet.INVENTORY_2;
import static com.acelerati.management_service.domain.util.PaginationUtil.DEFAULT_PAGE_SIZE;
import static com.acelerati.management_service.domain.util.PaginationUtil.NO_RECORDS_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryUseCaseTest {
    @Mock
    InventoryPersistencePort persistencePort;
    @InjectMocks
    InventoryUseCase inventoryUseCase;
    InventoryModel foundProduct;
    InventoryModel inventoryModel;

    @BeforeEach
    void setUp() {
        foundProduct = new InventoryModel();
        inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);

    }
    @Test
    void whenProductMotExistThenCreated() {
        when(this.persistencePort.getElementById(inventoryModel.getIdProduct())).thenReturn(Optional.empty());
        inventoryUseCase.addInventory(List.of(inventoryModel));
        verify(this.persistencePort).getElementById(inventoryModel.getIdProduct());
        verify(this.persistencePort).addInventory(inventoryModel);
    }
    @Test
    void whenProductoExistThenUpdateStockandSave(){
        when(this.persistencePort.getElementById(inventoryModel.getIdProduct())).thenReturn(Optional.of(inventoryModel));
        inventoryUseCase.addInventory(List.of(inventoryModel));
        verify(this.persistencePort).getElementById(inventoryModel.getIdProduct());
        verify(this.persistencePort).updateInventory(inventoryModel);
    }

    @Test
    void whenGetAllInventoryWithStockAndSalePriceGreaterThan0ThenReturnList() {
        List<InventoryModel> listInventory = new ArrayList<>();
        listInventory.add(inventoryModel);
        when(this.persistencePort.getAllInventoryWithStockAndSalePriceGreaterThan0()).thenReturn(listInventory);
        List<InventoryModel> responseList = inventoryUseCase.getAllInventoryWithStockAndSalePriceGreaterThan0();
        assertEquals(listInventory.size(), responseList.size());
        verify(this.persistencePort).getAllInventoryWithStockAndSalePriceGreaterThan0();
    }
   @Test
    void whenPriceIsUpdatedThenRespond(){
        when(this.persistencePort.getElementById(inventoryModel.getIdProduct())).thenReturn(Optional.of(inventoryModel));
        inventoryModel.setSalePrice(BigDecimal.valueOf(7000));
        inventoryUseCase.updatePriceSale(inventoryModel);
        verify(this.persistencePort).getElementById(inventoryModel.getIdProduct());
        verify(this.persistencePort).updateInventory(inventoryModel);
    }

    @Test
    void whenPriceIsUpdatedThenThrowsExceptionProductNoFound() {
        when(this.persistencePort.getElementById(inventoryModel.getIdProduct())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ProductNotFoundException.class, ()->inventoryUseCase.updatePriceSale(inventoryModel));
        verify(this.persistencePort).getElementById(inventoryModel.getIdProduct());
    }

    @Test
    void getInventoriesBy_whenFromRangeGreaterThanToRangeItShouldThrowException() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(250_000L, 180_000L, null, null);
        assertEquals(250_000L, searchCriteria.getFromSalePrice());
        assertEquals(180_000L, searchCriteria.getToSalePrice());
        InvalidFilterRangeException exception = assertThrows(InvalidFilterRangeException.class,
                () -> inventoryUseCase.getInventoriesBy(searchCriteria, null));
        assertEquals("The From range must be greater than the To range", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("providePairsOfPricesButOneIsNull")
    void getInventoriesBy_whenJustOneOfTheRangesIsDefinedItShouldThrowException(Long fromUnitPrice, Long toUnitPrice) {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(fromUnitPrice, toUnitPrice, null, null);
        InvalidFilterRangeException exception = assertThrows(InvalidFilterRangeException.class,
                () -> inventoryUseCase.getInventoriesBy(searchCriteria, null));
        assertEquals("If one of either the From or To ranges is defined, the other must be defined too.", exception.getMessage());
    }

    private static Stream<Arguments> providePairsOfPricesButOneIsNull() {
        return Stream.of(
                Arguments.of(null, 250_000L),
                Arguments.of(180_000L, null)
        );
    }

    @Test
    void getInventoriesBy_whenThePageSizeIsNullItShouldFallbackTo20() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(null, 1);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        assertEquals(DEFAULT_PAGE_SIZE, paginationUtil.getPageSize());
        assertEquals(1, paginationUtil.getPageNumber());
    }

    @Test
    void getInventoriesBy_onceTheSearchFinishedItShouldLeaveThePaginationReadyToServe() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(4, 1);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        // Doing this because the grand total depends on the SQL count.
        paginationUtil.setTotalResults(4L);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        assertEquals("Showing 1 to 4 of 4 results.", paginationUtil.getDescription());
        assertEquals(4L, paginationUtil.getTotalResults());
        assertEquals(0, paginationUtil.getFirstResultIndex());
        assertEquals(3, paginationUtil.getLastResultIndex());
    }

    @Test
    void getInventoriesBy_whenNoResultsFoundThePaginationResponseShouldBeOnEmptyState() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(4, 1);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(Collections.emptyList());
        // Doing this because the grand total depends on the SQL count.
        paginationUtil.setTotalResults(0L);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        assertEquals(NO_RECORDS_FOUND, paginationUtil.getDescription());
        assertEquals(0L, paginationUtil.getTotalResults());
        assertNull(paginationUtil.getFirstResultIndex());
        assertNull(paginationUtil.getLastResultIndex());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    void getInventoriesBy_paginationInfoIsCorrectWhenMoreThanOnePageIsPresent(int pageNumber) {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        final int pageSize = 4;
        final long totalResults = 20L;
        PaginationUtil paginationUtil = new PaginationUtil(pageSize, pageNumber);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        // Doing this because the grand total depends on the SQL count.
        paginationUtil.setTotalResults(totalResults);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        String expectedDescription = "";
        int expectedFirstIndex = 0;
        int expectedLastIndex = 0;
        if (pageNumber == 1) {
            expectedDescription = "Showing 1 to 4 of 20 results.";
            expectedLastIndex = 3;
        } else if (pageNumber == 2) {
            expectedDescription = "Showing 5 to 8 of 20 results.";
            expectedFirstIndex = 4;
            expectedLastIndex = 7;
        } else if (pageNumber == 5) {
            expectedDescription = "Showing 17 to 20 of 20 results.";
            expectedFirstIndex = 16;
            expectedLastIndex = 19;
        }
        assertEquals(expectedDescription, paginationUtil.getDescription());
        assertEquals(20L, paginationUtil.getTotalResults());
        assertEquals(expectedFirstIndex, paginationUtil.getFirstResultIndex());
        assertEquals(expectedLastIndex, paginationUtil.getLastResultIndex());

        tearDown();
    }

    @ParameterizedTest
    @MethodSource("provideTwoLastPages_irregular")
    void getInventoriesBy_paginationInfoIsCorrectWhenLastPageHasLessResultsThanPageSize(
            int pageNumber, List<InventoryModel> returnedInventories) {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        final int pageSize = 4;
        final long totalResults = 23L;
        PaginationUtil paginationUtil = new PaginationUtil(pageSize, pageNumber);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(returnedInventories);
        // Doing this because the grand total depends on the SQL count.
        paginationUtil.setTotalResults(totalResults);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        String expectedDescription = "";
        int expectedFirstIndex = 0;
        int expectedLastIndex = 0;
        if (pageNumber == 5) {
            expectedDescription = "Showing 17 to 20 of 23 results.";
            expectedFirstIndex = 16;
            expectedLastIndex = 19;
        } else if (pageNumber == 6) {
            expectedDescription = "Showing 21 to 23 of 23 results.";
            expectedFirstIndex = 20;
            expectedLastIndex = 22;
        }
        assertEquals(expectedDescription, paginationUtil.getDescription());
        assertEquals(23L, paginationUtil.getTotalResults());
        assertEquals(expectedFirstIndex, paginationUtil.getFirstResultIndex());
        assertEquals(expectedLastIndex, paginationUtil.getLastResultIndex());

        tearDown();
    }

    private static Stream<Arguments> provideTwoLastPages_irregular() {
        return Stream.of(
                Arguments.of(5, INVENTORY_1),
                Arguments.of(6, INVENTORY_2)
        );
    }

    private void tearDown() {
        Mockito.reset(persistencePort);
    }
}