package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.application.dto.response.AddProductToCartResponseDTO;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartResponseMapper {

    @Mapping(source = "id", target = "idProductCart")
    AddProductToCartResponseDTO toDTOResponse(CartInventoryModel cartInventoryModel);



}
