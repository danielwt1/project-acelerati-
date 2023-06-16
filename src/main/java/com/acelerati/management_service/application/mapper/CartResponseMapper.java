package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.response.CartDTO;
import com.acelerati.management_service.domain.model.CartModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartResponseMapper {
    CartDTO toDTO(CartModel cartModel);

}
