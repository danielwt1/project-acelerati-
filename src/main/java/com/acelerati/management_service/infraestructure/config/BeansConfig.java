package com.acelerati.management_service.infraestructure.config;


import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.usecase.InventoryUseCase;
import com.acelerati.management_service.infraestructure.output.adapter.InventoryJpaAdapter;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;
import com.acelerati.management_service.infraestructure.output.repository.impl.InventaryRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    private final InventaryRepositoryImpl inventaryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;

    public BeansConfig(InventaryRepositoryImpl inventaryRepository, InventoryEntityMapper inventoryEntityMapper) {
        this.inventaryRepository = inventaryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
    }

    // Toca crear un Bean para el Puerto de persistencia
    // Si tengo mas de una clase en mi proyecto pues tendria que hacerlo
    @Bean
    public InventoryPersistencePort inventoryPersistencePPort() {
        //Retorno donde implemento
        // se poasa lo que necesita el constructor
        return new InventoryJpaAdapter(inventaryRepository, inventoryEntityMapper);
    }
    //Lo mismo para el servicio
    @Bean
    public InventoryServicePort personServicePort(){
        return new InventoryUseCase(inventoryPersistencePPort());
    }
}
