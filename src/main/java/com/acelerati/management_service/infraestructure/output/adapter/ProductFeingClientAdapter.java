package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.application.driven.ProductsFeingClientPort;
import com.acelerati.management_service.infraestructure.output.feignClient.ProductClient;

import java.util.List;

public class ProductFeingClientAdapter implements ProductsFeingClientPort {

    private final ProductClient productClient;

    public ProductFeingClientAdapter(ProductClient productClient) {
        this.productClient = productClient;
    }
    @Override
    public List<?> getAllProducts() {
        return this.productClient.gettAll();
    }
}
