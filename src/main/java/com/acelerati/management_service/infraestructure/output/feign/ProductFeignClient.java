package com.acelerati.management_service.infraestructure.output.feign;

import com.acelerati.management_service.application.dto.response.BrandFeignClientResponseDTO;
import com.acelerati.management_service.application.dto.response.CategoryFeignClientResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductFeignClientResponseDTO;
import com.acelerati.management_service.infraestructure.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "products-microservice-api", url = "${external.products.microservice.api.base-url}",
        configuration = FeignConfiguration.class)
public interface ProductFeignClient {
    @GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductFeignClientResponseDTO> getProducts(@RequestParam Integer page, @RequestParam Integer itemsNumber);

    @GetMapping(value = {"/brands"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BrandFeignClientResponseDTO> getBrands();

    @GetMapping(value = {"/categories"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryFeignClientResponseDTO> getCategories();
}
