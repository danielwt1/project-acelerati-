package com.acelerati.management_service.infraestructure.output.feign;

import com.acelerati.management_service.application.dto.response.BrandDTO;
import com.acelerati.management_service.application.dto.response.CategoryDTO;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.config.feign.FeignClientInterceptor;
import com.acelerati.management_service.infraestructure.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "products-microservice-api", url = "${external.products.microservice.api.base-url}",
        configuration = {FeignClientInterceptor.class, FeignConfiguration.class})
public interface ProductFeignClient {
    @GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDTO> getProducts(@RequestParam Integer page, @RequestParam Integer itemsNumber);

    @GetMapping(value = {"/brands"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BrandDTO> getBrands(@RequestParam Integer page, @RequestParam Integer itemsNumber);

    @GetMapping(value = {"/categories"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDTO> getCategories(@RequestParam Integer page, @RequestParam Integer itemsNumber);

}
