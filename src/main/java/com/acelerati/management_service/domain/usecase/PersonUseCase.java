package com.acelerati.management_service.domain.usecase;

import com.example.hexagonalarch.domain.Exception.DomainNameIsEmptyException;
import com.example.hexagonalarch.domain.api.IPersonServicePort;
import com.example.hexagonalarch.domain.model.PersonaModel;
import com.example.hexagonalarch.domain.spi.IPersonaPersistencePort;

//Implementa  casos o los puertos de la seccion API del dominio
public class PersonUseCase implements IPersonServicePort {

    private final IPersonaPersistencePort personPersistencePort;

    public PersonUseCase(IPersonaPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public void saVe(PersonaModel persona) {
        if(persona.getNombrre().isEmpty()){
            throw new DomainNameIsEmptyException("El nombre no puede estar vacio Al hacer save");
        }
        this.personPersistencePort.save(persona);

    }
}
