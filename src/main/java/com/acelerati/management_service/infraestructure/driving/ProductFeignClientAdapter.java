package com.acelerati.management_service.infraestructure.driving;

import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.mapper.ProductResponseMapper;
import com.acelerati.management_service.domain.model.ProductModel;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;

import java.util.List;

public class ProductFeignClientAdapter implements ProductFeignClientPort {

    private final ProductRetriever productRetriever;
    private final ProductResponseMapper productResponseMapper;

    public ProductFeignClientAdapter(ProductRetriever productRetriever, ProductResponseMapper productResponseMapper) {
        this.productRetriever = productRetriever;
        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public List<ProductModel> fetchProductsFromMicroservice() {
        return productResponseMapper.toProductModelList(productRetriever.getAllProducts());
    }
}
