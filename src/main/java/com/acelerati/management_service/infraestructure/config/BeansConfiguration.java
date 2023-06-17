package com.acelerati.management_service.infraestructure.config;
import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.driven.UserFeignClientPort;
import com.acelerati.management_service.domain.api.CartInventoryServicePort;
import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.api.SaleServicePort;
import com.acelerati.management_service.domain.spi.*;
import com.acelerati.management_service.domain.usecase.CartInventoryUseCase;
import com.acelerati.management_service.domain.usecase.CartUseCase;
import com.acelerati.management_service.domain.usecase.InventoryUseCase;
import com.acelerati.management_service.domain.usecase.SaleUseCase;
import com.acelerati.management_service.infraestructure.output.adapter.*;
import com.acelerati.management_service.infraestructure.output.mapper.*;
import com.acelerati.management_service.infraestructure.output.repository.*;
import com.acelerati.management_service.infraestructure.output.retriever.ProductRetriever;
import com.acelerati.management_service.infraestructure.output.retriever.UserAuthenticationRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    private final CartInventoryRepository cartInventoryRepository;
    private final CartInventoryEntityMapper cartInventoryEntityMapper;
    private final InventoryRepository inventoryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;
    private final ProductRetriever productRetriever;
    private final UserAuthenticationRetriever userAuthenticationRetriever;
    private final CartEntityMapper cartEntityMapper;
    private final CartRepository cartRepository;
    private final SaleRepository saleRepository;
    private final SaleEntityMapper saleEntityMapper;

    public BeansConfiguration(CartInventoryRepository cartInventoryRepository,
                              CartInventoryEntityMapper cartInventoryEntityMapper,
                              InventoryRepository inventoryRepository,
                              InventoryEntityMapper inventoryEntityMapper, ProductRetriever productRetriever,
                              UserAuthenticationRetriever userAuthenticationRetriever,
                              CartEntityMapper cartEntityMapper, CartRepository cartRepository,
                              SaleRepository saleRepository,
                              SaleEntityMapper saleEntityMapper) {        this.cartInventoryRepository = cartInventoryRepository;
        this.cartInventoryEntityMapper = cartInventoryEntityMapper;
        this.inventoryRepository = inventoryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
        this.productRetriever = productRetriever;
        this.userAuthenticationRetriever = userAuthenticationRetriever;
        this.cartEntityMapper = cartEntityMapper;
        this.cartRepository = cartRepository;
        this.saleRepository = saleRepository;
        this.saleEntityMapper = saleEntityMapper;
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
    public UserFeignClientPort userFeignClientPort() {
        return new UserFeignClientAdapter(userAuthenticationRetriever);
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

    @Bean
    public SalePersistencePort salePersistencePort() {
        return new SaleJpaAdapter(saleRepository, saleEntityMapper);
    }


    @Bean
    public SaleServicePort saleServicePort() {
        return new SaleUseCase(salePersistencePort(), inventoryPersistencePPort());
    }}
