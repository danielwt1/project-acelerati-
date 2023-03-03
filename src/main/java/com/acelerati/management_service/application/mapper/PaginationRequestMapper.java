package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.domain.model.PaginationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationRequestMapper {
    PaginationModel toModel(PaginationDTO paginationDTO);
}
