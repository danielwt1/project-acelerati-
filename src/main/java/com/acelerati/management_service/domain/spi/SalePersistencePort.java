package com.acelerati.management_service.domain.spi;

import com.acelerati.management_service.domain.model.SaleModel;

public interface SalePersistencePort {

    SaleModel createSale(SaleModel saleModel);

    SaleModel findSaleById(Long idSale);
}
