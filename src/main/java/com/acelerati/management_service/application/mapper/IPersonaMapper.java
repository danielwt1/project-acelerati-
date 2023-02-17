package com.acelerati.management_service.application.mapper;

import com.example.hexagonalarch.application.dto.request.PersonDTO;
import com.example.hexagonalarch.domain.model.PersonaModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonaMapper {
    PersonaModel toObject(PersonDTO personaDTO);
}
