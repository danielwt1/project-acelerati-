package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.output.feign.ProductFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRetriever {

    private static final Logger logger = LoggerFactory.getLogger(ProductRetriever.class);

    private final ProductFeignClient productFeignClient;

    public ProductRetriever(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @HystrixCommand(fallbackMethod = "getEmptyProductList")
    public List<ProductDTO> getAllProducts(Integer page, Integer itemsNumber) {
        ResponseEntity<List<ProductDTO>> response = productFeignClient.getProducts(page, itemsNumber);
        return response.getBody();
    }

    public List<ProductDTO> getEmptyProductList(Integer page, Integer itemsNumber) {
        logger.error("Could not get connection to the Products Microservice. Try again later");
        return new ArrayList<>();
    }
}
