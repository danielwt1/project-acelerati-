package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.domain.spi.SaleInventoryPersistencePort;
import com.acelerati.management_service.infraestructure.output.mapper.SaleInventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.SaleInventoryEntityMapper2;
import com.acelerati.management_service.infraestructure.output.repository.SaleInventoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaleInventoryJpaAdapter implements SaleInventoryPersistencePort {
    private final SaleInventoryRepository saleInventoryRepository;
    private final SaleInventoryEntityMapper2 saleInventoryEntityMapper;

    @Override
    public void saveAllSaleInventories(List<SaleInventoryModel> saleInventoryModels) {
        saleInventoryRepository.saveAllSaleInventories(saleInventoryEntityMapper.toListEntity(saleInventoryModels));
    }
}
