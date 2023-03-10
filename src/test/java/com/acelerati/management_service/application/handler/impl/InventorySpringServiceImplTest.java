package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static com.acelerati.management_service.application.utils.ApplicationDataSet.INVENTORY_1;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class InventorySpringServiceImplTest {
    @Mock
    InventoryServicePort inventoryServicePort;
    @Mock
    InventoryRequestMapper inventoryRequestMapper;
    @InjectMocks
    InventorySpringServiceImpl inventoryImpl;
    @Test
    void pruebaTest(){
        List<InventoryDTO> list = new ArrayList<>();
        this.inventoryImpl.addInventory(list);
    }
/*
    @Test
    void whenGetInventoriesByCalledWithNoFiltersAndDefaultPagination_thenItReturnsAllProducts() {
        InventorySearchCriteriaDTO searchCriteriaDTO = new InventorySearchCriteriaDTO(null, null, null);
        PaginationDTO paginationDTO = new PaginationDTO(null, 1);
        when(inventoryServicePort.getInventoriesBy(Mockito.any(), Mockito.any())).thenReturn(INVENTORY_1);

        FilterInventoryResponseDTO filterInventoryResponse = inventoryImpl.getInventoriesBy(searchCriteriaDTO, paginationDTO);

        Assertions.assertNotNull(filterInventoryResponse, "Applying no filter to the inventory and requesting the default pagination should not return null");
        Assertions.assertNotNull(filterInventoryResponse.getInventoryResponseDTOs(), "Applying no filter to the inventory ");
    }
    */
}