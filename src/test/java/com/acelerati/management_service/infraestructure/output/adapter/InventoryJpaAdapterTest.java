package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryJpaAdapterTest {

    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    InventoryEntityMapper inventoryEntityMapper;
    @InjectMocks
    InventoryJpaAdapter inventoryJpaAdapter;
    InventoryModel inventoryModel;
    InventoryEntity inventoryEntity;

    @BeforeEach
    void setUp() {
        inventoryModel = new InventoryModel(1L, "producto", 5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L, 1L);
        inventoryEntity = new InventoryEntity(1L, "producto", 5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L, 1L);
    }


    @Test
    void whenCallAddInventoryWithEntityThenSaveDB() {
        this.inventoryJpaAdapter.addInventory(inventoryModel);

    }

    @Test
    void whenFindElementByIdThenReturnEntity() {
        this.inventoryJpaAdapter.getElementById(1L);
    }
    @Test
    void whenFindElementByIdIsEmptyThenReturnEntity() {
        when(this.inventoryRepository.getElementById(inventoryModel.getId())).thenReturn(Optional.of(inventoryEntity));
        when(this.inventoryEntityMapper.toModel(inventoryEntity)).thenReturn(inventoryModel);
        this.inventoryJpaAdapter.getElementById(inventoryModel.getIdProduct());
    }

    @Test
    void whenUpdateProductoThenCallUpdateDB() {
        this.inventoryJpaAdapter.updateInventory(inventoryModel);
    }
}