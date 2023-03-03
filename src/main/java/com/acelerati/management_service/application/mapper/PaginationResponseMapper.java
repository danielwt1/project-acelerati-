package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.response.PaginationResponseDTO;
import com.acelerati.management_service.domain.model.PaginationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationResponseMapper {
    PaginationResponseDTO toResponseDTO(PaginationModel paginationModel);
}
