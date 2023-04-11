package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.exception.UnavailableMicroserviceException;
import com.acelerati.management_service.infraestructure.output.feign.ProductFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRetriever {

    private final ProductFeignClient productFeignClient;

    public ProductRetriever(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @HystrixCommand(fallbackMethod = "throwUnavailableMicroserviceException")
    public List<ProductDTO> getAllProducts(Integer page, Integer itemsNumber) throws UnavailableMicroserviceException {
        ResponseEntity<List<ProductDTO>> response = productFeignClient.getProducts(page, itemsNumber);
        return response.getBody();
    }

    public List<ProductDTO> throwUnavailableMicroserviceException(Integer page, Integer itemsNumber)
            throws UnavailableMicroserviceException {
        throw new UnavailableMicroserviceException("Products service unavailable, try again later.");
    }
}
