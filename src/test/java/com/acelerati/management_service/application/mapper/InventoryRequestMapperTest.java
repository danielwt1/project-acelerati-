package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InventoryRequestMapperTest {

    InventoryModel inventoryModel;
    InventoryDTO inventoryDTO;
    @BeforeEach
    void setUp(){
        inventoryDTO = new InventoryDTO("producto",5000L, BigDecimal.valueOf(5000),1L, 1L);
        inventoryModel = new InventoryModel(1L,"producto",5000L, BigDecimal.valueOf(5000),BigDecimal.valueOf(6000), 1L,1L);
    }
    @Test
    void toModel() {
        InventoryRequestMapper inventoryRequestMapper = mock(InventoryRequestMapper.class);

        inventoryRequestMapper.toModel(inventoryDTO);
        verify(inventoryRequestMapper).toModel(inventoryDTO);
    }

    @Test
    void toListModel() {
        InventoryRequestMapper inventoryRequestMapper = mock(InventoryRequestMapper.class);

        inventoryRequestMapper.toListModel(List.of(inventoryDTO));
        verify(inventoryRequestMapper).toListModel(List.of(inventoryDTO));
    }
}