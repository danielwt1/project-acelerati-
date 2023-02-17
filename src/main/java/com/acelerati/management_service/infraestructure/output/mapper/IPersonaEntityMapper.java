package com.acelerati.management_service.infraestructure.output.mapper;

import com.example.hexagonalarch.application.dto.request.PersonDTO;
import com.example.hexagonalarch.domain.model.PersonaModel;
import com.example.hexagonalarch.infraestructure.output.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonaEntityMapper {

    PersonaEntity toEntity(PersonaModel personaModel);
    PersonaModel entityToModel(PersonaEntity personaEntity);
    List<PersonaModel> toListModelDomain(List<PersonaEntity> listaEntidadPersona);
}
