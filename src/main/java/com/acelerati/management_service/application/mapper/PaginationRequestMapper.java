package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.PaginationRequestDTO;
import com.acelerati.management_service.domain.util.PaginationUtil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationRequestMapper {
    PaginationUtil toPaginationUtil(PaginationRequestDTO paginationRequestDTO);
}
