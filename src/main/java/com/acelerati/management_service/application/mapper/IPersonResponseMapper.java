package com.acelerati.management_service.application.mapper;

import com.example.hexagonalarch.application.dto.request.PersonDTO;
import com.example.hexagonalarch.domain.model.PersonaModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonResponseMapper {

    PersonDTO toDto(PersonaModel personaModel);
    //Para listas
    List<PersonDTO> listModelToDto(List<PersonaModel> listPersonaModel);
}
