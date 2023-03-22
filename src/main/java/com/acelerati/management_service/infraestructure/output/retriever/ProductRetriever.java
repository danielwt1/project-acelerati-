package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.output.feign.ProductFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRetriever {

    private final ProductFeignClient productFeignClient;

    public ProductRetriever(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @HystrixCommand(fallbackMethod = "getFakeProducts")
    public List<ProductDTO> getAllProducts() {
        return productFeignClient.getProducts();
    }

    public List<ProductDTO> getFakeProducts() {
        List<ProductDTO> fakeProducts = new ArrayList<>();
        fakeProducts.add(new ProductDTO(1L, "Mother board", "A mother board", "Model 1", 1L, 1L));
        fakeProducts.add(new ProductDTO(4L, "USB Memory 64GB", "A USB Memory 64GB", "Model 4", 4L, 2L));
        return fakeProducts;
    }
}
