package com.acelerati.management_service.application.handler.impl;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;


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


}