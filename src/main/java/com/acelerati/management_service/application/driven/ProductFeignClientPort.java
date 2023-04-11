package com.acelerati.management_service.application.driven;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.exception.UnavailableMicroserviceException;

import java.util.List;

public interface ProductFeignClientPort {
    List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) throws UnavailableMicroserviceException;
}
