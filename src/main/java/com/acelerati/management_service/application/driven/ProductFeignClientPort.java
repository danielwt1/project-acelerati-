package com.acelerati.management_service.application.driven;

import com.acelerati.management_service.domain.model.ProductModel;

import java.util.List;

public interface ProductFeignClientPort {
    List<ProductModel> fetchProductsFromMicroservice();

}
