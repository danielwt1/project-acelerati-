package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.exception.SaleNotFoundException;
import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.domain.spi.SalePersistencePort;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.mapper.SaleEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.SaleInventoryEntityMapper2;
import com.acelerati.management_service.infraestructure.output.repository.SaleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleJpaAdapter implements SalePersistencePort {

    private final SaleRepository saleRepository;
    private final SaleEntityMapper saleEntityMapper;
    private final SaleInventoryEntityMapper2 saleInventoryEntityMapper;

    @Override
    public SaleModel createSale(SaleModel saleModel) {
        SaleEntity newSaleEntity = saleRepository.createSale(saleEntityMapper.toEntity(saleModel));
        return saleEntityMapper.toModel(newSaleEntity);
    }

    @Override
    public SaleModel findSaleById(Long idSale) {
        SaleEntity foundSaleEntity = saleRepository.findById(idSale).orElseThrow(
                () -> new SaleNotFoundException(String.format("Sale with ID %d not found", idSale)));
        SaleModel toReturn = saleEntityMapper.toModel(foundSaleEntity);
        toReturn.setPurchasedItems(
                saleInventoryEntityMapper.toSaleInventoryModelList(foundSaleEntity.getPurchasedItems()));
        return toReturn;
    }
}
