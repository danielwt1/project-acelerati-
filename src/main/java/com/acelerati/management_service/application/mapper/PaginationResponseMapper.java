package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.response.PaginationResponseDTO;
import com.acelerati.management_service.domain.util.PaginationUtil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationResponseMapper {
    PaginationResponseDTO toResponseDTO(PaginationUtil paginationModel);
}
