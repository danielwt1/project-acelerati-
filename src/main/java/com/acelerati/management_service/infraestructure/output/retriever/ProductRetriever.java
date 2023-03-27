package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.response.BrandDTO;
import com.acelerati.management_service.application.dto.response.CategoryDTO;
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
    public List<ProductDTO> getAllProducts(Integer page, Integer itemsNumber) {
        return productFeignClient.getProducts(page, itemsNumber);
    }

    public List<ProductDTO> getFakeProducts() {
        List<ProductDTO> fakeProducts = new ArrayList<>();
        fakeProducts.add(new ProductDTO(1L, "Mother board", "A mother board", "Model 1",
                1L, 1L));
        fakeProducts.add(new ProductDTO(4L, "USB Memory 64GB", "A USB Memory 64GB", "Model 4",
                4L, 2L));
        return fakeProducts;
    }

    @HystrixCommand(fallbackMethod = "getFakeBrands")
    public List<BrandDTO> getAllBrands(Integer page, Integer itemsNumber) {
        return productFeignClient.getBrands(page, itemsNumber);
    }

    public List<BrandDTO> getFakeBrands() {
        List<BrandDTO> fakeBrands = new ArrayList<>();
        fakeBrands.add(new BrandDTO(1L, "Logitech"));
        fakeBrands.add(new BrandDTO(2L, "Razer"));
        fakeBrands.add(new BrandDTO(3L, "Corsair"));
        fakeBrands.add(new BrandDTO(4L, "Newskill"));
        return fakeBrands;
    }

    @HystrixCommand(fallbackMethod = "getFakeCategories")
    public List<CategoryDTO> getAllCategories(Integer page, Integer itemsNumber) {
        return productFeignClient.getCategories(page, itemsNumber);
    }

    public List<CategoryDTO> getFakeCategories() {
        List<CategoryDTO> fakeCategories = new ArrayList<>();
        fakeCategories.add(new CategoryDTO(1L, "Pantallas"));
        fakeCategories.add(new CategoryDTO(2L, "Teclados"));
        fakeCategories.add(new CategoryDTO(3L, "Mouse"));
        fakeCategories.add(new CategoryDTO(4L, "HDD"));
        return fakeCategories;
    }

}
