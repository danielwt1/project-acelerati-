package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.infraestructure.output.feign.ProductFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

import static com.acelerati.management_service.application.utils.ApplicationDataSet.PRODUCT_MICROSERVICE_RESPONSE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRetrieverTest {
    @Mock
    private ProductFeignClient productFeignClient;
    @InjectMocks
    private ProductRetriever productRetriever;

    @Test
    void getInventoriesBy_whenFeignClientCallSucceedTheEntityBodyShouldBeAvailable() {
        ResponseEntity<List<ProductDTO>> fakeResponse = ResponseEntity.ok(PRODUCT_MICROSERVICE_RESPONSE_1);
        when(productFeignClient.getProducts(Mockito.anyInt(), Mockito.anyInt())).thenReturn(fakeResponse);

        List<ProductDTO> products = productRetriever.getAllProducts(1, 1000);

        assertNotNull(products);
        assertEquals(8, products.size());
    }

    @ParameterizedTest
    @MethodSource("providePagesAndItemNumbers")
    void getInventoriesBy_whenFallbackMethodCalledItIsAlwaysReturningAnEmptyArrayList(int page, int itemsNumber) {
        List<ProductDTO> products = productRetriever.getEmptyProductList(page, itemsNumber);
        assertNotNull(products);
        assertEquals(0, products.size());
    }

    private static Stream<Arguments> providePagesAndItemNumbers() {
        return Stream.of(
                Arguments.of(1, 1000),
                Arguments.of(-1, 0),
                Arguments.of(0, 0),
                Arguments.of(-1, -1000)
        );
    }
}
