package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.domain.exception.SaleNotFoundException;
import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.domain.spi.SalePersistencePort;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.mapper.SaleEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.SaleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleJpaAdapter implements SalePersistencePort {

    private final SaleRepository saleRepository;
    private final SaleEntityMapper saleEntityMapper;

    @Override
    public SaleModel createSale(SaleModel saleModel) {
        SaleEntity newSaleEntity = saleRepository.createSale(saleEntityMapper.toEntity(saleModel));
        return saleEntityMapper.toModel(newSaleEntity);
    }

    @Override
    public void updateSale(SaleModel saleModel) {
        saleRepository.save(saleEntityMapper.toEntity(saleModel));
    }

    @Override
    public SaleModel findSaleById(Long idSale) {
        SaleEntity foundSaleEntity = saleRepository.findById(idSale).orElseThrow(
                () -> new SaleNotFoundException(String.format("Sale with ID %d not found", idSale)));
        return saleEntityMapper.toModel(foundSaleEntity);
    }

    @Override
    public void rejectSale(Long idSale) {
        SaleEntity saleToReject = saleRepository.findById(idSale).orElseThrow(
                () -> new SaleNotFoundException(String.format("Sale with ID %d not found", idSale)));
        saleToReject.setStatus(SaleModel.STATUS_REJECTED);
        saleRepository.save(saleToReject);
    }

    @Override
    public void approveSale(Long idSale) {
        SaleEntity saleToApprove = saleRepository.findById(idSale).orElseThrow(
                () -> new SaleNotFoundException(String.format("Sale with ID %d not found", idSale)));
        saleToApprove.setStatus(SaleModel.STATUS_APPROVED);
        saleRepository.save(saleToApprove);
    }
}
