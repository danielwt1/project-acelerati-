package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.SaleModel;

public interface SalePersistencePort {

    SaleModel createSale(SaleModel saleModel);

    void updateSale(SaleModel saleModel);

    SaleModel findSaleById(Long idSale);
    void rejectSale(Long idSale);

    void approveSale(Long idSale);
}
