package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.acelerati.management_service.application.utils.ApplicationDataSet.PRODUCT_MICROSERVICE_RESPONSE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFeignClientAdapterTest {
    @Mock
    private ProductRetriever productRetriever;
    @InjectMocks
    private ProductFeignClientAdapter productFeignClientAdapter;

    @Test
    void getInventoriesBy_whenFetchProductsFromMicroserviceSucceedItShouldReturnTheProductList() {
        when(productRetriever.getAllProducts(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(PRODUCT_MICROSERVICE_RESPONSE_1);

        List<ProductDTO> products = productFeignClientAdapter.fetchProductsFromMicroservice(1, 1000);

        assertNotNull(products);
        assertEquals(8, products.size());
    }
}
