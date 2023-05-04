package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;

import java.util.List;

public class ProductFeignClientAdapter implements ProductFeignClientPort {

    private final ProductRetriever productRetriever;

    public ProductFeignClientAdapter(ProductRetriever productRetriever) {
        this.productRetriever = productRetriever;
    }

    @Override
    public List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) {
        return productRetriever.getAllProducts(page, itemsNumber);
    }
}
