package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.PaginationResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.domain.util.PaginationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {
    @Mock
    private InventorySpringService inventorySpringService;

    @InjectMocks
    private InventoryController inventoryRestController;

    @Test
    void addInventory_shouldReturnCreatedResponse(){
        // Given
        List<InventoryDTO> inventoryDTOList = Arrays.asList(new InventoryDTO("producto",5000L, BigDecimal.valueOf(5000), 1L,1L), new InventoryDTO("producto",5000L,BigDecimal.valueOf(5000), 1L,1L));
         // When
        ResponseEntity<Void> responseEntity = inventoryRestController.addInventory("admin",inventoryDTOList);
        // Then
        verify(inventorySpringService).addInventory(inventoryDTOList);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void getInventoryBy_shouldReturnOkResponseWhen() {
        InventorySearchCriteriaDTO searchCriteriaRequest = new InventorySearchCriteriaDTO(null, null, null);
        PaginationDTO paginationRequest = new PaginationDTO(null, 1);

        PaginationResponseDTO emptyPaginationResponse = new PaginationResponseDTO(PaginationUtil.DEFAULT_PAGE_SIZE, 1, null,
                null, null, PaginationUtil.NO_RECORDS_FOUND);
        FilterInventoryResponseDTO noResultsResponse = new FilterInventoryResponseDTO(new ArrayList<>(), emptyPaginationResponse);

        when(inventorySpringService.getInventoriesBy(searchCriteriaRequest, paginationRequest)).thenReturn(noResultsResponse);
        ResponseEntity<FilterInventoryResponseDTO> responseEntity = inventoryRestController.getInventoriesBy("admin",searchCriteriaRequest, paginationRequest);

        verify(inventorySpringService).getInventoriesBy(searchCriteriaRequest, paginationRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void whenGetAllProductsForSaleThenReturnHttpStatusOk(){
        ProductsForSaleDTO productsForSaleDTO = new ProductsForSaleDTO(2L,"producto1",new BigDecimal(3000),3L,"Pp112");
        List<ProductsForSaleDTO> listProductsForSale = Arrays.asList(productsForSaleDTO);
        when(this.inventorySpringService.getAllProductForSale("producto1","Pp112","cat1",1,20)).thenReturn(listProductsForSale);
        ResponseEntity<List<ProductsForSaleDTO>>response = this.inventoryRestController.getAllProductsForSale("daniel","producto1","Pp112","cat1",1,20);
        assertAll(
                ()-> assertEquals(listProductsForSale.size(),response.getBody().size()),
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->verify(this.inventorySpringService).getAllProductForSale("producto1","Pp112","cat1",1,20)
        );
    }
    void updateSalePrice_shouldReturnOkStatus() {
        // Given
        InventoryUpdateRequestDTO inventoryDTO = new InventoryUpdateRequestDTO("producto",5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L,1L);
        // When
        ResponseEntity<Void> responseEntity = inventoryRestController.updateSalePrice("user",inventoryDTO);
        // Then
        verify(inventorySpringService).updateProductSalePrice(inventoryDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}