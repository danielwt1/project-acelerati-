package com.acelerati.management_service.infraestructure.config;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.domain.api.CartInventoryServicePort;
import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.spi.CartInventoryPersistencePort;
import com.acelerati.management_service.domain.spi.CartPersistencePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.usecase.CartInventoryUseCase;
import com.acelerati.management_service.domain.usecase.CartUseCase;
import com.acelerati.management_service.domain.usecase.InventoryUseCase;
import com.acelerati.management_service.infraestructure.output.adapter.CartInventoryJpaAdapter;
import com.acelerati.management_service.infraestructure.output.adapter.CartJpaAdapter;
import com.acelerati.management_service.infraestructure.output.adapter.ProductFeignClientAdapter;
import com.acelerati.management_service.infraestructure.output.adapter.InventoryJpaAdapter;
import com.acelerati.management_service.infraestructure.output.mapper.CartEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.CartInventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepository;
import com.acelerati.management_service.infraestructure.output.repository.CartRepository;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    private final CartInventoryRepository cartInventoryRepository;
    private final CartInventoryEntityMapper cartInventoryEntityMapper;
    private final InventoryRepository inventoryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;
    private final ProductRetriever productRetriever;

    private final CartEntityMapper cartEntityMapper;
     private final CartRepository cartRepository;

    public BeansConfiguration(CartInventoryRepository cartInventoryRepository, CartInventoryEntityMapper cartInventoryEntityMapper, InventoryRepository inventoryRepository, InventoryEntityMapper inventoryEntityMapper, ProductRetriever productRetriever, CartEntityMapper cartEntityMapper, CartRepository cartRepository) {
        this.cartInventoryRepository = cartInventoryRepository;
        this.cartInventoryEntityMapper = cartInventoryEntityMapper;
        this.inventoryRepository = inventoryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
        this.productRetriever = productRetriever;
        this.cartEntityMapper = cartEntityMapper;
        this.cartRepository = cartRepository;
    }
    @Bean
    public InventoryPersistencePort inventoryPersistencePPort() {
        return new InventoryJpaAdapter(inventoryRepository, inventoryEntityMapper);
    }
    @Bean
    public InventoryServicePort personServicePort(){
        return new InventoryUseCase(inventoryPersistencePPort());
    }

    @Bean
    public ProductFeignClientPort productFeignClientPort() {
        return new ProductFeignClientAdapter(productRetriever);
    }

    @Bean
    public CartInventoryPersistencePort cartInventoryPersistencePort(){
        return new CartInventoryJpaAdapter(cartInventoryRepository,cartInventoryEntityMapper);
    }
    @Bean
    public CartInventoryServicePort cartInventoryServicePort(){
        return new CartInventoryUseCase(cartInventoryPersistencePort());
    }
    @Bean
    public CartPersistencePort cartPersistencePort(){
        return new CartJpaAdapter(cartRepository,cartEntityMapper);
    }
    @Bean
    public CartServicePort cartServicePort(){
        return new CartUseCase(cartPersistencePort());
    }



}
