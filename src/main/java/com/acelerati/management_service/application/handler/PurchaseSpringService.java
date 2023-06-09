package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.response.ProductDTO;

import java.util.List;

public interface PurchaseSpringService {
    String createPurchaseRequest(Long idUser);

    void performSales(List<String> saleIds);

    List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber);
}
