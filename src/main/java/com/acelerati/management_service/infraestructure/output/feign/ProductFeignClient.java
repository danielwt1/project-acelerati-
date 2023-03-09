package com.acelerati.management_service.infraestructure.output.feign;

import com.acelerati.management_service.application.dto.response.ProductFeignClientResponseDTO;
import com.acelerati.management_service.infraestructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "products-microservice-api", url = "${external.products.microservice.api.base-url}",
        configuration = FeignClientConfiguration.class)
public interface ProductFeignClient {
    @GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductFeignClientResponseDTO> getProducts();
}
