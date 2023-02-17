package com.acelerati.management_service.infraestructure.output.adapter;

import com.example.hexagonalarch.domain.model.PersonaModel;
import com.example.hexagonalarch.domain.spi.IPersonaPersistencePort;
import com.example.hexagonalarch.infraestructure.output.entity.PersonaEntity;
import com.example.hexagonalarch.infraestructure.output.mapper.IPersonaEntityMapper;
import com.example.hexagonalarch.infraestructure.output.repository.PersonaRepository;

public class PersonaJPAAdapter implements IPersonaPersistencePort {

    private final PersonaRepository personaRepository;
    private final IPersonaEntityMapper personaEntityMapper;

    public PersonaJPAAdapter(PersonaRepository personaRepository, IPersonaEntityMapper personaEntityMapper) {
        this.personaRepository = personaRepository;
        this.personaEntityMapper = personaEntityMapper;
    }

    @Override
    public PersonaModel save(PersonaModel personaModel) {
        PersonaEntity per = this.personaRepository.save(this.personaEntityMapper.toEntity(personaModel));
        return this.personaEntityMapper.entityToModel(per);

    }

    //Deberian ir lso metodos que se suan para persistir cruds. busquedas- etc
}
