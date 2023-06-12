package com.acelerati.management_service.domain.api;

import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.SaleModel;

public interface SaleServicePort {
    Long createSale(Long idUser, CartModel cart);

    SaleModel findSaleById(String idSale);

    void decreaseStock(SaleModel saleModel);

    void rejectSale(Long idSale);

    void approveSale(Long idSale);
}
