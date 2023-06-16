package com.acelerati.management_service.application.driven;

import com.acelerati.management_service.application.dto.response.ProductDTO;

import java.util.List;

public interface ProductFeignClientPort {
    List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber);
}
