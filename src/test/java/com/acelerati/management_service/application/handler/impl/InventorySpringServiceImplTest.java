package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;

import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventorySpringServiceImplTest {
    @Mock
    InventoryServicePort inventoryServicePort;
    @Mock
    InventorySearchMapper inventorySearchMapper;
    @Mock
    InventoryRequestMapper inventoryRequestMapper;
    @Mock
    ProductFeignClientPort productFeignClient;
    @InjectMocks
    InventorySpringServiceImpl inventoryImpl;
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
    void whenCalldataPaginatedThenReturnListPaginatedTest(){
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
        when(this.productFeignClient.fetchProductsFromMicroservice()).thenReturn(productDTOS);
        List<ProductsForSaleDTO> productsForSale = this.inventoryImpl.getAllProductForSale("producto","marca","nombreCat",1,5);
        assertEquals(2,productsForSale.size());
    }

}