package com.acelerati.management_service.application.handler.impl;

import com.example.hexagonalarch.application.dto.request.PersonDTO;
import com.example.hexagonalarch.application.handler.IPersonaHandler;
import com.example.hexagonalarch.application.mapper.IPersonResponseMapper;
import com.example.hexagonalarch.application.mapper.IPersonaMapper;
import com.example.hexagonalarch.domain.api.IPersonServicePort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Capa servicio
@Service
@Transactional
public class PersonaHandlerImpl implements IPersonaHandler {
    private final IPersonServicePort personServicePort;

    private final IPersonaMapper personaMapper;
    private final IPersonResponseMapper personResponseMapper;

    public PersonaHandlerImpl(IPersonServicePort personServicePort, IPersonaMapper personaMapper, IPersonResponseMapper personResponseMapper) {
        this.personServicePort = personServicePort;
        this.personaMapper = personaMapper;
        this.personResponseMapper = personResponseMapper;
    }

    @Override
    public void savePersona(PersonDTO personDTO) {
            this.personServicePort.saVe(this.personaMapper.toObject(personDTO));
    }
}
