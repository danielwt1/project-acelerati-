package com.acelerati.management_service.infraestructure.config;

import com.example.hexagonalarch.application.mapper.IPersonaMapper;
import com.example.hexagonalarch.domain.api.IPersonServicePort;
import com.example.hexagonalarch.domain.spi.IPersonaPersistencePort;
import com.example.hexagonalarch.domain.usecase.PersonUseCase;
import com.example.hexagonalarch.infraestructure.output.adapter.PersonaJPAAdapter;
import com.example.hexagonalarch.infraestructure.output.mapper.IPersonaEntityMapper;
import com.example.hexagonalarch.infraestructure.output.repository.PersonaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    private final PersonaRepository personaRepository;
    private final IPersonaEntityMapper personaEntityMapper;

    public BeansConfig(PersonaRepository personaRepository, IPersonaEntityMapper personaEntityMapper) {
        this.personaRepository = personaRepository;
        this.personaEntityMapper = personaEntityMapper;
    }

    // Toca crear un Bean para el Puerto de persistencia
    // Si tengo mas de una clase en mi proyecto pues tendria que hacerlo
    @Bean
    public IPersonaPersistencePort personaPersistencePort() {
        //Retorno donde implemento
        // se poasa lo que necesita el constructor
        return new PersonaJPAAdapter(personaRepository, personaEntityMapper);
    }
    //Lo mismo para el servicio
    @Bean
    public IPersonServicePort personServicePort(){
        return new PersonUseCase(personaPersistencePort());
    }
}
