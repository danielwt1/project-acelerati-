package com.acelerati.management_service.infraestructure.config;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
import com.acelerati.management_service.domain.usecase.InventoryUseCase;
import com.acelerati.management_service.infraestructure.output.adapter.InventoryJpaAdapter;
import com.acelerati.management_service.infraestructure.output.mapper.InventoryEntityMapper;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    private final InventoryRepository inventoryRepository;
    private final InventoryEntityMapper inventoryEntityMapper;
    private final InventorySearchMapper inventorySearchMapper;

    public BeansConfiguration(InventoryRepository  inventoryRepository, InventoryEntityMapper inventoryEntityMapper,
                              InventorySearchMapper inventorySearchMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryEntityMapper = inventoryEntityMapper;
        this.inventorySearchMapper = inventorySearchMapper;
    }

    // Toca crear un Bean para el Puerto de persistencia
    // Si tengo mas de una clase en mi proyecto pues tendria que hacerlo
    @Bean
    public InventoryPersistencePort inventoryPersistencePPort() {
        //Retorno donde implemento
        // se poasa lo que necesita el constructor
        return new InventoryJpaAdapter(inventoryRepository, inventoryEntityMapper);
    }
    //Lo mismo para el servicio
    @Bean
    public InventoryServicePort personServicePort(){
        return new InventoryUseCase(inventoryPersistencePPort());
    }
}
