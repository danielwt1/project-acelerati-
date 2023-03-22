package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}