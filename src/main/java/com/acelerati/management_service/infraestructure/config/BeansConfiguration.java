package com.acelerati.management_service.infraestructure.config;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.mapper.ProductResponseMapper;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.usecase.InventoryUseCase;
import com.acelerati.management_service.infraestructure.output.adapter.ProductFeignClientAdapter;
import com.acelerati.management_service.infraestructure.output.adapter.InventoryJpaAdapter;
import com.acelerati.management_service.infraestructure.output.feign.ProductFeignClient;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    private final InventoryRepository inventoryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;
    private final ProductResponseMapper productResponseMapper;
    private final ProductFeignClient productClient;
    private final ProductRetriever productRetriever;

        public BeansConfiguration(InventoryRepository inventoryRepository, InventoryEntityMapper inventoryEntityMapper, ProductFeignClient productClient, InventorySearchMapper inventorySearchMapper,
				ProductResponseMapper productResponseMapper, ProductRetriever productRetriever) {

        this.inventoryRepository = inventoryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
		this.productResponseMapper = productResponseMapper;
        this.productRetriever = productRetriever;
        this.productClient = productClient;
    }

    @Bean
    public InventoryPersistencePort inventoryPersistencePPort() {
        return new InventoryJpaAdapter(inventoryRepository, inventoryEntityMapper);
    }
    //Lo mismo para el servicio
    @Bean
    public InventoryServicePort personServicePort(){
        return new InventoryUseCase(inventoryPersistencePPort());
    }
    @Bean
    public ProductFeignClientPort productFeignClientPort() {
        return new ProductFeignClientAdapter(productRetriever, productResponseMapper);
    }}
