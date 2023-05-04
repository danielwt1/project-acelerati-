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
        PaginationUtil paginationUtil = new PaginationUtil(null, 1L);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        assertEquals(DEFAULT_PAGE_SIZE, paginationUtil.getPageSize());
        assertEquals(1, paginationUtil.getPageNumber());
    }

    @Test
    void getInventoriesBy_whenThePageSizeIsSpecifiedItShouldNotFallbackIt() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(null, null, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(10L, 1L);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil);
        assertNotEquals(20L, paginationUtil.getPageSize());
        assertEquals(10L, paginationUtil.getPageSize());
    }

    @Test
    void getInventoriesBy_whenTheRangesAreWellSpecifiedItShouldNotThrowAnyException() {
        InventorySearchCriteriaUtil searchCriteria = new InventorySearchCriteriaUtil(180_000L, 250_000L, null, null);
        PaginationUtil paginationUtil = new PaginationUtil(null, 1L);
        when(persistencePort.getInventoriesBy(Mockito.any(), Mockito.eq(paginationUtil))).thenReturn(INVENTORY_1);
        assertDoesNotThrow(() -> inventoryUseCase.getInventoriesBy(searchCriteria, paginationUtil));
    }
}
