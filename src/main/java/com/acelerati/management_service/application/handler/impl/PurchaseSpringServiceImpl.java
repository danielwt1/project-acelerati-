package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.driven.QueueClientPort;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.application.handler.PurchaseSpringService;
import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.api.SaleServicePort;
import com.acelerati.management_service.domain.exception.SaleNotFoundException;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.SaleModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseSpringServiceImpl implements PurchaseSpringService {
    private final CartServicePort cartServicePort;
    private final SaleServicePort saleServicePort;
    private final QueueClientPort queueClientPort;
    private final ProductFeignClientPort productFeignClientPort;

    @Override
    public String createPurchaseRequest(Long idUser) {
        CartModel cartModel = cartServicePort.getCartByUserId(idUser);
        log.debug("Extracted cart: {}", cartModel);
        Long saleID = saleServicePort.createSale(idUser, cartModel);
        cartServicePort.deleteCartByIdUser(idUser);
        queueClientPort.submitPurchaseRequest(saleID);
        return "The purchase request was sent successfully. A confirmation e-mail will be delivered soon.";
    }

    @Override
    public void performSales(List<String> saleIds) {
        try {
            for (String saleId: saleIds) {
                SaleModel foundSale = saleServicePort.findSaleById(saleId);
                log.debug("Sale recovered with {} items.", foundSale.getPurchasedItems().size());

            }
        } catch (SaleNotFoundException e) {
            log.debug("Sale will not be performed.", e);
        }
    }

    private void validateProducts() {

    }

    @Override
    public List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) {
        return productFeignClientPort.fetchProductsFromMicroservice(page, itemsNumber);
    }
}
